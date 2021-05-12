import {Component, Input, OnChanges} from '@angular/core';
import {CalendarOptions} from '@fullcalendar/angular';

@Component({
  selector: 'app-training',
  templateUrl: './training-manager.component.html',
  styleUrls: ['./training-manager.component.css']
})
export class TrainingManagerComponent implements OnChanges  {
  @Input() response: any | null = null;
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

  constructor() {
  }

  ngOnChanges(): void {
    this.prepareCalendarFields();
  }

  private prepareCalendarFields(): void {
    if (!!this.response) {
      const EVENTS: any[] = [];
      this.response.trainings.forEach((training: any) => {
        EVENTS.push({
          title: training.title,
          start: training.trainingDate,
          url: `training/${training.id}`,
        });
      });
      this.calendarOptions.events = EVENTS;
    }
  }
}
