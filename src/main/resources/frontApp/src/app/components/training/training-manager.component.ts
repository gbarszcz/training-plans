import {Component} from '@angular/core';
import {CalendarOptions} from '@fullcalendar/angular';
import {AppService} from '../../app.service';

@Component({
  selector: 'app-training',
  templateUrl: './training-manager.component.html',
  styleUrls: ['./training-manager.component.css']
})
export class TrainingManagerComponent {
  response: any | null = null;
  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
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

  constructor(private appService: AppService) {
    this.prepareCalendarFields();
  }

  private prepareCalendarFields(): void {
    this.appService.apiGetRequest('account/trainings-plans').subscribe(
      (res: any) => {
        res.trainings.forEach((training: any) => {
            const EVENTS: any[] = [];
            EVENTS.push({
              title: training.title,
              start: training.trainingDate,
              url: `training/${training.id}`,
            });
            this.calendarOptions.events = EVENTS;
          }
        );
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
}
