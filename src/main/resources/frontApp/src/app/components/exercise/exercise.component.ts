import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css']
})
export class ExerciseComponent implements OnInit {
  @Input() response: any | null = null;

  constructor() { }

  ngOnInit(): void {
  }

}
