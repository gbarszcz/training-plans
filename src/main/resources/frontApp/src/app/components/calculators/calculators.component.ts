import { Component, OnInit } from '@angular/core';
import {AppService} from "../../app.service";



@Component({
  selector: 'app-calculators',
  templateUrl: './calculators.component.html',
  styleUrls: ['./calculators.component.css']
})
export class CalculatorsComponent implements OnInit {
  response: any | null = null;
  weight = 0;
  height = 0;

  constructor(private service: AppService) {
    //service.apiGetRequest()
  }

  public calculateBMI(weight: any, height: number): void {
    this.weight = weight;
    this.height = height;
  }

  ngOnInit(): void {
  }

}
