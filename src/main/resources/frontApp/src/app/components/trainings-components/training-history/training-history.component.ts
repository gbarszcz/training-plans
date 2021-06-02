import {Component} from '@angular/core';
import {AppService} from '../../../app.service';
import {IAlert} from '../../../models/IAlert';

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
  response: any[] = [];
  alerts: IAlert[] = [];

  constructor(private service: AppService) {
    this.prepareFields();
  }

  private prepareFields(): void {
    this.service.apiGetRequest('training/history').subscribe(
      (res: any) => {
        this.response = res || [];
        this.sortBy();
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  sortBy(index: number = 0): void {
    this.sortOptionIndex = index;
    if (this.sortOptions[index].name === 'Date') {
      this.response = this.response?.sort((x: any, y: any) => {
          return new Date(x.trainingDate).getTime() - new Date(y.trainingDate).getTime();
      });
    } else {
      this.response = this.response?.sort((x: any, y: any) => {
        return this.getTrainingName(x, index).localeCompare(this.getTrainingName(y, index));
      });
    }
    if (this.sortOptions[index].icon === 'caret-up') {
      this.response = this.response?.reverse();
    }
  }

  private getTrainingName(training: any, index: number): string {
    return training[this.sortOptions[index].name.toLowerCase()] || 'Undefined';
  }

  dateFormatted(date: string): string {
    return (new Date(date)).toLocaleString('en-GB', {
      year: 'numeric', month: 'long', day: 'numeric'
    });
  }

  delete(trainingId: string): void {
    this.service.apiDeleteRequest(`training/history/${trainingId}`).subscribe(
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
