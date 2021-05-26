import {Component} from '@angular/core';
import {AppService} from '../../app.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css']
})
export class ExerciseComponent {
  response: any | null = null;

  constructor(private appService: AppService, private route: ActivatedRoute) {
    this.prepareFields();
  }

  private prepareFields(): void {
    this.appService.apiGetRequest('exercise/' + this.route.snapshot.params.id).subscribe(
      (res: any) => {
        this.response = res;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
}
