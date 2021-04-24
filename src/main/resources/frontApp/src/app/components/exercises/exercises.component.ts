import {Component, Input, OnInit} from '@angular/core';
import {IPageContent} from '../../models/IPageContent'; // todo temporary

@Component({
  selector: 'app-exercises',
  templateUrl: './exercises.component.html',
  styleUrls: ['./exercises.component.css']
})
export class ExercisesComponent implements OnInit {
  @Input() content: IPageContent[] = []; // todo any

  constructor() { }

  ngOnInit(): void {
  }

}
