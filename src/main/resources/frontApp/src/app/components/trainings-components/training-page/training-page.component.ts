import {Component} from '@angular/core';
import {IAlert} from '../../../models/IAlert';
import {AppService} from '../../../app.service';
import {ActivatedRoute} from '@angular/router';
import {ITraining} from '../../../models/ITraining';

@Component({
  selector: 'app-training-page',
  templateUrl: './training-page.component.html',
  styleUrls: ['./training-page.component.css']
})
export class TrainingPageComponent {
  endpoint = 'training/history';
  alerts: IAlert[] = [];
  training: ITraining = {
    id: 0,
    difficulty: 'undefined',
    date: new Date().toISOString().substring(0, 10),
    title: 'undefined',
    training: 'undefined',
    unit: [
      {
        id: 0,
        exercise: {
          id: 0,
          difficulty: 'undefined',
          name: 'undefined',
          description: 'undefined',
          equipments: 'undefined',
        },
        series: [],
      }
    ],
  };

  constructor(private service: AppService, private route: ActivatedRoute) {
    this.prepareFields();
  }

  private prepareFields(): void {
    this.service.apiGetRequest(this.endpoint + '/' + this.route.snapshot.params.id).subscribe(
      (res: any) => {
        this.training.id = res.id;
        this.training.difficulty = res.difficulty || 'undefined';
        this.training.date = new Date(res.trainingDate).toISOString().substring(0, 10);
        this.training.title = res.title;
        res.trainingSeriesData.forEach((series: any) => {
          series.trainingUnit -= 1;
          const SERIES = this.training.unit[series.trainingUnit]?.series || [];
          SERIES.push({
            trainingUnit: (series.trainingUnit + 1),
            id: series.trainingSeriesResultData.id,
            weight: series.trainingSeriesResultData.additionalWeight || 0,
            reps: series.trainingSeriesResultData.iterationCount || 0,
            unit: this.unit(series.trainingSeriesResultData.iterationUnit),
          });
          this.training.unit[series.trainingUnit] = {
            id: series.id,
            exercise: {
              id: series.exercise.id,
              name: series.exercise.exerciseName,
              difficulty: series.exercise.exerciseDifficultyLvl,
              description: series.exercise.exerciseDescription,
              equipments: series.exercise.equipments,
            },
            series: SERIES,
          };
        });
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  private unit(unit: string | null): string {
    switch (unit) {
      case 'SECONDS':
        return 's';
      default:
        return 'kg';
    }
  }

  save(): void {
    this.service.apiPutRequest(this.endpoint, this.mapFormData()).subscribe(
      (res: any) => {
        this.alerts.push({
          id: this.alerts.length,
          show: true,
          header: 'Success!',
          text: 'Training modified',
          level: 'success',
          displayHideButton: true
        });
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

  private mapFormData(): any {
    const REQUEST = {
      id: this.training.id,
      title: this.training.title,
      trainingDate: this.training.date,
      trainingSeriesData: []
    };

    this.training.unit.forEach((unit) => {
      unit.series.forEach((series) => {
        (REQUEST.trainingSeriesData as any[]).push({
          id: unit.id,
          exerciseId: unit.exercise.id,
          trainingSeriesResultData: {
            id: series.id,
            iterationCount: series.reps,
            iterationUnit: this.iterationUnit(series.unit),
            additionalWeight: series.weight,
          },
          trainingUnit: series.trainingUnit
        });
      });
    });

    return REQUEST;
  }

  private iterationUnit(unit: string): string {
    switch (unit) {
      case 's':
        return 'SECONDS';
      default:
        return 'QUANTITY';
    }
  }
}
