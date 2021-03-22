import {Component, OnInit} from '@angular/core';
import {AppService} from './app.service';
import {Navigation} from './models/Navigation';
import {Router} from '@angular/router';
import {ISocialMedia} from './models/ISocialMedia';
import {IPageContent} from './models/IPageContent';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  navigation: Navigation | null = null;
  pageContent: IPageContent[] = [];
  pageType = 'section';
  socialMedia: ISocialMedia[] = [];
  url: string;

  constructor(private appService: AppService, private router: Router, private location: Location) {
    this.url = location.path() || '/';
  }

  changeURL(URL: string): void {
    this.url = URL;
    this.router.navigate([this.url]);

    this.prepareFields();
  }

  ngOnInit(): void {
    this.prepareFields();

    this.socialMedia = JSON.parse(this.appService.getSocialMedia()).socialMedia || [];
  }

  prepareFields(): void {
    this.navigation = new Navigation(this.appService.getNavigation(this.url), this.url) || null;

    try {
      const TMP = JSON.parse(this.appService.getPageContent(this.url));
      this.pageType = TMP.type;
      this.pageContent = TMP.sections;
    }
    catch (e) {
      this.pageType = '';
      this.pageContent = [];
    }
  }
}
