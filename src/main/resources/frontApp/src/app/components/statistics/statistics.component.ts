import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { ChartDataSets } from 'chart.js';
import {IPeriod, Period} from '../../models/Period';
import {IAlert} from '../../models/IAlert';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  response: any | null = null;
  alerts: IAlert[] = [];
  exercises: Exercise[] | null = null
  periods: IPeriod[] = []
  activePeriod: IPeriod = { value: '', viewValue: 'All' }
  selectedExercise: Exercise | null = null
  selectedStatistics: Statistics | null = null

  chartData: ChartDataSets[] = []
  dates: string[] = []

  constructor(private appService: AppService) {
    this.periods = new Period().values
  }

  ngOnInit(): void {
    this.getExercises()
  }

  getExercises() {
    this.appService.apiGetRequest('exercises').subscribe(
      (res: any) => {
        this.exercises = res;
        if (this.exercises) {
          this.selectedExercise = this.exercises[0]
        }
        if (this.selectedExercise) {
          this.getStatistics(this.selectedExercise.id)
        }
      },
      (error: any) => {
        this.alerts.push({
          id: this.alerts.length,
          show: true,
          header: 'Sorry! We have encountered a problem...',
          text: 'Please try again later :\'(',
          level: 'danger',
          displayHideButton: true
        });
      }
    );
  }

  getStatistics(exerciseId: number) {
    this.appService.apiGetRequest('statistic/' + exerciseId).subscribe(
      (res: any) => {
        this.response = res
        this.selectedStatistics = res;
        this.selectedExercise = this.selectedStatistics?.exercise || null
        this.refreshData()
      },
      (error: any) => {
        this.alerts.push({
          id: this.alerts.length,
          show: true,
          header: 'Sorry! We have encountered a problem...',
          text: 'Please try again later :\'(',
          level: 'danger',
          displayHideButton: true
        });
      }
    );
  }

  periodChange(period: IPeriod) {
    let params = {
      period: period.value,
      duration: 1
    }
    let periodParams = period.value === '' ? '' : "/" + params.period + "/" + params.duration
    this.appService.apiGetRequest("statistic/" + this.selectedStatistics?.exercise.id + periodParams).subscribe(
      (res: any) => {
        this.response = res
        this.activePeriod = period
        this.refreshData()
      },
      (error: any) => {
        this.alerts.push({
          id: this.alerts.length,
          show: true,
          header: 'Sorry! We have encountered a problem...',
          text: 'Please try again later :\'(',
          level: 'danger',
          displayHideButton: true
        });
      }
    )
  }

  refreshData() {
    this.selectedStatistics = this.response === null ? {} : this.response
    let statistics = this.selectedStatistics?.exerciseStats?.sort((s1, s2) => new Date(s1.date).getTime() - new Date(s2.date).getTime())
    this.dates = statistics?.map(statistic => new Date(statistic.date).toLocaleDateString('pl-PL')) || []
    this.chartData = [{
      'data': this.selectedStatistics?.exerciseStats?.map(s => s.result),
      'label' : this.selectedStatistics?.exercise?.exerciseName
    }]
  }

  exerciseChange(exercise: Exercise) {
    this.getStatistics(exercise.id)
  }

  hideAlert(alertID: string): void {
    this.alerts[+alertID].show = false;
  }
}

export interface Exercise {
  id: number,
  exerciseName: string,
  exerciseDescription: string
}

export interface Statistics {
  exercise: Exercise,
  exerciseStats: ExerciseStats[],
  aggregates: Aggregates
}

export interface ExerciseStats {
  date: Date,
  iterationCount: number,
  result: number
}

export interface Aggregates {
  maxReps: number,
  minReps: number,
  avgReps: number
}
