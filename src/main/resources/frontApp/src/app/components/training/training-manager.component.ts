import {Component} from '@angular/core';
import {CalendarOptions} from '@fullcalendar/angular';

@Component({
  selector: 'app-training',
  templateUrl: './training-manager.component.html',
  styleUrls: ['./training-manager.component.css']
})
export class TrainingManagerComponent {
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
    events: [
      {
        title: 'Training - push',
        start: '2021-04-05 17:45'
      },
      {
        title: 'Training - pull',
        start: '2021-04-07 17:35'
      },
      {
        title: 'Training - legs',
        start: '2021-04-09 11:45'
      },
    ],
  };

  constructor() {
  }
}
