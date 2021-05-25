import {Component, Input, OnInit} from '@angular/core';
import {ESearchOption} from 'src/app/enums/ESearchOption';
import {AppService} from '../../app.service'; // todo temporary

@Component({
  selector: 'app-exercises',
  templateUrl: './exercises.component.html',
  styleUrls: ['./exercises.component.css']
})
export class ExercisesComponent {
  response: any | null = null;

  searchOption: ESearchOption = ESearchOption.exercises;

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

  ngOnInit(): void {
  }

  setResults(results: any) {
    this.response = results;
  }

}
