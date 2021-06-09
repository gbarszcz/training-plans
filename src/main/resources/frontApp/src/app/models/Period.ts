export interface IPeriod {
  value: string;
  viewValue: string;
}

export class Period {
  readonly values : IPeriod[];

  constructor() {
    this.values = [
      { value: 'weeks', viewValue: 'Week' },
      { value: 'months', viewValue: 'Month' },
      { value: 'years', viewValue: 'Year' }
    ];
  }
}


