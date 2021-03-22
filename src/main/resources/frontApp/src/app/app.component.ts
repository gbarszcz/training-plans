import {Component, OnInit} from '@angular/core';
import {AppService} from './app.service';
import {Navigation} from './models/Navigation';
import {Router} from '@angular/router';
import {ISocialMedia} from './models/ISocialMedia';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  navigation: Navigation | null = null;
  socialMedia: ISocialMedia[] = [];
  url = '/';

  constructor(private appService: AppService, private router: Router) { }

  changeURL(URL: string): void {
    this.url = URL;
    this.router.navigate([this.url]);

    this.navigation = new Navigation(this.appService.getNavigation(this.url), this.url) || null;
  }

  ngOnInit(): void {
    this.url = this.router.url;
    this.navigation = new Navigation(this.appService.getNavigation(this.url), this.url) || null;

    this.socialMedia = JSON.parse(this.appService.getSocialMedia()).socialMedia || [];
  }
}
