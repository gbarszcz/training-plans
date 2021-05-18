import { Component, Input } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/angular';
import { AppService } from '../../app.service';
import { IAlert } from '../../models/IAlert';

@Component({
  selector: 'app-training',
  templateUrl: './training-manager.component.html',
  styleUrls: ['./training-manager.component.css']
})
export class TrainingManagerComponent {
  @Input() response: any | null = null;
  mainEndpoint = 'training/history';
  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    dateClick: this.handleDateClick.bind(this),
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    },
    buttonText: {
      prev: 'prev',
      next: 'next'
    },
    themeSystem: 'bootstrap',
    height: '100%',
    events: [],
  };
  trainingsTemplates: any[] = [];
  trainingFormData: any = {};
  events: any[] = [];
  alerts: IAlert[] = [];
  sendMethod = 'post';

  constructor(private service: AppService) {
    this.prepareCalendarFields();
  }

  private prepareCalendarFields(): void {
    this.service.apiGetRequest('account/trainings-plans').subscribe(
      (res: any) => {
        res.trainings.forEach((training: any) => {
          this.events.push({
            title: training.title,
            start: training.trainingDate,
            url: `training/${training.id}`,
          });
          this.calendarOptions.events = this.events;
        }
        );
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  private eventRemove(id: number): void {
    this.events.splice(
      this.events.indexOf(
        this.events.filter((e: any) => {
          return e.id === id;
        })[0]
      ),
      1
    );
    this.calendarOptions.events = this.events;
  }

  setTemplate(templateId: number): void {
    this.trainingFormData.templateId = templateId;
  }

  handleDateClick(arg: any): void {
    this.sendMethod = 'post';
    this.trainingFormData.trainingDate = arg.dateStr;
    document.querySelector<HTMLElement>('[data-target="#addTraining"]')?.click();
  }

  upsertTraining(): void {
    if (this.sendMethod) {
      this.post();
    } else {
      this.put();
    }
  }

  private post(): void {
    this.service.apiPostRequest(this.mainEndpoint, this.trainingFormData).subscribe(
      (res: any) => {
        this.alerts.push({
          id: this.alerts.length,
          show: true,
          header: 'Success!',
          text: 'Training added to your calendar',
          level: 'success',
          displayHideButton: true
        });
        this.events.push({
          id: res.trainingId,
          title: this.trainingFormData.title,
          start: this.trainingFormData.trainingDate,
          url: `training/${res.trainingId}`,
        });
        this.calendarOptions.events = this.events;
      },
      (error: any) => {
        this.alerts.push({
          id: this.alerts.length,
          show: true,
          header: 'Sorry! We have encountered a problem!',
          text: 'This training has not been added to your calendar. Please try again later.',
          level: 'danger',
          displayHideButton: true
        });
      }
    );
  }

  private put(): void {
    this.service.apiPutRequest(this.mainEndpoint, this.trainingFormData).subscribe(
      (res: any) => {
        this.alerts.push({
          id: this.alerts.length,
          show: true,
          header: 'Success!',
          text: 'Training modified',
          level: 'success',
          displayHideButton: true
        });
        this.events.filter((event: any) => {
          return event.id = res.trainingId;
        }).forEach((event: any) => {
          event = {
            title: this.trainingFormData.title,
            start: this.trainingFormData.trainingDate,
          };
        });
        this.calendarOptions.events = this.events;
      },
      (error: any) => {
        this.alerts.push({
          id: this.alerts.length,
          show: true,
          header: 'Sorry! We have encountered a problem!',
          text: 'This training has not been added to your calendar. Please try again later.',
          level: 'danger',
          displayHideButton: true
        });
      }
    );
  }
}
