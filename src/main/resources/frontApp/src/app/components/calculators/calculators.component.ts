import { Component, OnInit } from '@angular/core';
import {AppService} from '../../app.service';



@Component({
  selector: 'app-calculators',
  templateUrl: './calculators.component.html',
  styleUrls: ['./calculators.component.css']
})
export class CalculatorsComponent implements OnInit {
  response: any | null = null;
  weightBMI = 0;
  heightBMI = 0;
  resultBMI = 0;
  resultCI = 0;
  weightCI = 0;
  heightCI = 0;
  hipCircumference = 0;
  waist = 0;

  constructor(private service: AppService) {
  }

  ngOnInit(): void {
  }

  public calculateBMI(weightBMI: number, heightBMI: number): void {
    this.weightBMI = weightBMI;
    this.heightBMI = heightBMI;
    this.service.apiGetRequest('calculate', {params: {
      calculatorType: 'BMI',
      weight: this.weightBMI,
      height: this.heightBMI}, observe: 'response'}).subscribe(
        (res: any) => {
        this.response = res;
        this.resultBMI = this.response.body;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  public calculateCI(weightCI: number, heightCI: number): void {
    this.weightCI = weightCI;
    this.heightCI = heightCI;
    this.service.apiGetRequest('calculate', {params: {
        calculatorType: 'CI',
        weight: this.weightCI,
        height: this.heightCI}, observe: 'response'}).subscribe(
      (res: any) => {
        this.response = res;
        this.resultCI = this.response.body;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
}
