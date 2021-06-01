import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { ChartDataSets } from 'chart.js';
import {IPeriod, Period} from '../../models/Period';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  response: any | null = null;
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
        console.error(error);
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
        console.error(error);
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
