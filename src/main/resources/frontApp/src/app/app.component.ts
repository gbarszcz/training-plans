import {Component} from '@angular/core';
import {AppService} from './app.service';
import {Navigation} from './models/Navigation';
import {ISocialMedia} from './models/ISocialMedia';
import {Location} from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  navigation: Navigation | null = null;
  socialMedia: ISocialMedia[] = [];

  constructor(private appService: AppService, private location: Location) {
    this.prepareFields();
  }

  prepareFields(): void {
    const URL = this.location.path() || '/';
    this.navigation = new Navigation(this.appService.getNavigation(URL), URL) || null;
    try {
      this.socialMedia = JSON.parse(this.appService.getSocialMedia()).socialMedia || [];
    }
    catch (e) {
      console.error(e.message);
    }
  }
}
