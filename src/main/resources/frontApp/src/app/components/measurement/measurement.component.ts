import { Component } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { IAlert } from 'src/app/models/IAlert';
import {IPeriod, Period} from '../../models/Period';

@Component({
  selector: 'app-measurement',
  templateUrl: './measurement.component.html',
  styleUrls: ['./measurement.component.css']
})
export class MeasurementComponent {
  response: any | null = null;
  measurements: Measurement[] = []
  alerts: IAlert[] = [];
  formData: any = {};
  periods: IPeriod[] = []
  activePeriod: IPeriod = { value: '', viewValue: 'All' }
  dates: string[] = []
  measurementData: any = {}

  constructor(private service: AppService) {
    this.periodChange(this.activePeriod)
    this.periods = new Period().values
  }

  returnIfHasKey(obj: any, key: string): any | boolean {
    return key in obj ? obj[key] : false;
  }

  refreshData() {
    this.measurements = this.response === null ? [] : this.response
    let measurementData = this.measurements
      .sort((m1, m2) => new Date(m1.measurementDate).getTime() - new Date(m2.measurementDate)
        .getTime())
    this.dates = measurementData.map(measurement => new Date(measurement.measurementDate).toLocaleDateString('pl-PL'))
    this.measurementData = {
      measurements: [
        {
          value: '',
          icon: 'bi-calendar-date',
          placeholder: 'Measurement date',
          name: 'measurementDate',
          type: 'date',
          error: ''
        },
        {
          value: '',
          icon: 'bi-rulers',
          placeholder: 'Height',
          name: 'height',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.height),
              'label': 'Height'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          placeholder: 'Weight',
          name: 'weight',
          type: 'number',
          error: '',
          min: 0,
          max: 500,
          chartData: [
            {
              'data': measurementData.map(m => m.weight),
              'label': 'Weight'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Calf',
          name: 'calf',
          type: 'number',
          error: '',
          min: 10,
          max: 100,
          chartData: [
            {
              'data': measurementData.map(m => m.calf),
              'label': 'Calf'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Thigh',
          name: 'thigh',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.thigh),
              'label': 'Thigh'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Loins',
          name: 'loins',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.loins),
              'label': 'Loins'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Hips',
          name: 'hips',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.height),
              'label': 'Hips'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Waist',
          name: 'waist',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.waist),
              'label': 'Waist'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Abdomen',
          name: 'abdomen',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.abdomen),
              'label': 'Abdomen'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Chest',
          name: 'chest',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.chest),
              'label': 'Chest'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Shoulders',
          name: 'shoulders',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.shoulders),
              'label': 'Shoulders'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Triceps',
          name: 'triceps',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.triceps),
              'label': 'Triceps'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Biceps',
          name: 'biceps',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.biceps),
              'label': 'Biceps'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Forearm',
          name: 'forearm',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.forearm),
              'label': 'Forearm'
            }
          ]
        },
        {
          value: '',
          icon: 'bi-rulers',
          require: true,
          placeholder: 'Wrist',
          name: 'wrist',
          type: 'number',
          error: '',
          min: 50,
          max: 250,
          chartData: [
            {
              'data': measurementData.map(m => m.wrist),
              'label': 'Wrist'
            }
          ]
        }
      ]
    }
  }

  periodChange(period: IPeriod) {
    let params = {
      period: period.value,
      duration: 1
    }
    let periodParams = period.value === '' ? '' : "/" + params.period + "/" + params.duration
    this.service.apiGetRequest("measurement" + periodParams).subscribe(
      (res: any) => {
        this.response = res
        this.activePeriod = period
        this.refreshData()
      }
    )
  }

  post(): void {
    this.service.apiPostRequest('measurement', this.formData)
      .subscribe(
        (res: any) => {
          this.alerts.push({
            id: this.alerts.length,
            show: true,
            header: 'Ok!',
            text: 'Your changes have been saved',
            level: 'success',
            displayHideButton: true
          });
          this.periodChange(this.activePeriod)
        },
        (error: any) => {
          if (error.status === 400) {
            this.prepareErrorFields(error.error.errors);
          } else {
            this.alerts.push({
              id: this.alerts.length,
              show: true,
              header: 'Sorry! We have encountered a problem...',
              text: 'Please try again later :\'(',
              level: 'danger',
              displayHideButton: true
            });
          }
        }
      );
  }

  private prepareErrorFields(errors: any[]): void {
    ['measurements'].forEach((variant: string) => {
      errors.forEach((error) => {
        this.measurementData[variant.toString()].filter((input: any) => {
          return input.name === error.field;
        })[0].error = error.message;
      });
    });
  }

  hideAlert(alertID: string): void {
    this.alerts[+alertID].show = false;
  }
}

export interface Measurement {
  id: number,
  measurementDate: Date,
  height: number,
  weight: number,
  calf: number,
  thigh: number,
  loins: number,
  hips: number,
  waist: number,
  abdomen: number,
  chest: number,
  shoulders: number,
  triceps: number,
  biceps: number,
  forearm: number,
  wrist: number
}
