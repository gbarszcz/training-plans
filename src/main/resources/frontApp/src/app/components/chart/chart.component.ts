import { I18NHtmlParser } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label } from 'ng2-charts';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {
  @Input() data: ChartDataSets[] = []
  @Input() labels: Label[] = []

  lineChartOptions = {
    responsive: true,
  };
  mainColor = getComputedStyle(document.body).getPropertyValue('--bs-main')
  lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: this.mainColor,
    },
  ];

  lineChartLegend = true;
  lineChartPlugins = [];
  lineChartType: ChartType = 'line'
  constructor() { }

  ngOnInit(): void {
  }

}