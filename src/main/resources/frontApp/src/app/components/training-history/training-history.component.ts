import {Component} from '@angular/core';
import {AppService} from '../../app.service';
import {IAlert} from '../../models/IAlert';

@Component({
  selector: 'training-history',
  templateUrl: './training-history.component.html',
  styleUrls: ['./training-history.component.css']
})
export class TrainingHistoryComponent {
  sortOptionIndex = 0;
  sortOptions = [
    {
      name: 'Date',
      icon: 'caret-down'
    },
    {
      name: 'Date',
      icon: 'caret-up'
    },
    {
      name: 'Title',
      icon: 'caret-down'
    },
    {
      name: 'Title',
      icon: 'caret-up'
    },
    {
      name: 'Difficulty',
      icon: 'caret-down'
    },
    {
      name: 'Difficulty',
      icon: 'caret-up'
    }
  ];
  response: any[] = [
    {
      title: 'title 1',
      trainingDate: '2021-05-15T16:53:19.670Z',
      difficulty: 'hard'
    },
    {
      title: 'title 2',
      trainingDate: '2021-05-16T16:53:19.670Z',
      difficulty: 'low'
    },
    {
      title: 'title 3',
      trainingDate: '2021-05-17T16:53:19.670Z',
      difficulty: 'medium'
    },
  ];
  alerts: IAlert[] = [];

  constructor(private service: AppService) {
    this.prepareFields();
    this.sortBy(this.sortOptionIndex);
  }

  private prepareFields(): void {
    this.service.apiGetRequest('training/history').subscribe(
      (res: any) => {
        this.response = res || [];
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  sortBy(index: number): void {
    this.sortOptionIndex = index;
    this.response = this.response?.sort((x: any, y: any) => {
      const SORT_BY = this.sortOptions[index].name;
      if (SORT_BY === 'Date') {
        return new Date(x.trainingDate).getTime() - new Date(x.trainingDate).getTime();
      }
      return x[this.sortOptions[index].name.toLowerCase()].localeCompare(y[this.sortOptions[index].name.toLowerCase()]);
    });
    if (this.sortOptions[index].icon === 'caret-up') {
      this.response = this.response?.reverse();
    }
  }

  dateFormatted(date: string): string {
    return (new Date(date)).toLocaleString('en-GB', {
      year: 'numeric', month: 'long', day: 'numeric'
    });
  }

  delete(trainingId: string): void {
    this.service.apiDeleteRequest('training/history', {id: trainingId}).subscribe(
      (res: any) => {
        this.alerts.push({
          id: this.alerts.length,
          show: true,
          header: 'Success!',
          text: 'Training deleted',
          level: 'success',
          displayHideButton: true
        });
        this.response = this.response.filter((training: any) => {
          return training.id !== trainingId;
        });
      },
      (error: any) => {
        this.alerts.push({
          id: this.alerts.length,
          show: true,
          header: 'Sorry! We have encountered a problem!',
          text: 'This training has not been deleted. Please try again later.',
          level: 'danger',
          displayHideButton: true
        });
      }
    );
  }
}
