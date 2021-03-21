import { Component } from '@angular/core';
import {AppService} from './app.service';
import {Navigation} from './utils/Navigation';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  private appService: AppService;
  navigation: Navigation | null;

  constructor(navigationService: AppService) {
    this.appService = navigationService;

    this.navigation = null;
  }
}
