import {Component} from '@angular/core';
import {AppService} from '../../app.service'; // todo temporary

@Component({
  selector: 'app-exercises',
  templateUrl: './exercises.component.html',
  styleUrls: ['./exercises.component.css']
})
export class ExercisesComponent {
  response: any | null = null;

  constructor(private appService: AppService) {
    this.prepareFields();
  }

  private prepareFields(): void {
    this.appService.apiGetRequest('exercises').subscribe(
      (res: any) => {
        this.response = res;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
}
