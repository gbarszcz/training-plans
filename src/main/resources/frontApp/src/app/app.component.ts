import {Component, OnInit} from '@angular/core';
import {AppService} from './app.service';
import {Navigation} from './utils/Navigation';
import {Router} from '@angular/router';
import {ISocialMedia} from './models/ISocialMedia';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  navigation: Navigation | null = null;
  socialMedia: ISocialMedia[] = [];

  constructor(private appService: AppService, private router: Router) {
  }

  ngOnInit(): void {
    this.navigation = new Navigation(this.appService.getNavigation(this.router.url), this.router.url) || null;

    this.socialMedia = JSON.parse(this.appService.getSocialMedia()).socialMedia || [];
  }
}
