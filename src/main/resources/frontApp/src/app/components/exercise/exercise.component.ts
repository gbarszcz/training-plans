import {Component} from '@angular/core';
import {AppService} from '../../app.service';

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css']
})
export class ExerciseComponent {
  response: any | null = null;

  constructor(private appService: AppService) {
    this.prepareFields();
  }

  private prepareFields(): void {
    this.appService.apiGetRequest('profile').subscribe(
      (res: any) => {
        this.response = res;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
}
