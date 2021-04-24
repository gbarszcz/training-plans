import {Component, OnInit} from '@angular/core';
import {AppService} from './app.service';
import {Navigation} from './models/Navigation';
import {Router} from '@angular/router';
import {ISocialMedia} from './models/ISocialMedia';
import {IPageContent} from './models/IPageContent';
import {Location} from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  navigation: Navigation | null = null;
  socialMedia: ISocialMedia[] = [];
  mockPageContent: IPageContent[] = [];
  pageContent: Response | null = null;
  pageType = 'section';
  url: string;

  constructor(private appService: AppService, private router: Router, private location: Location) {
    this.url = location.path() || '/';
  }

  ngOnInit(): void {
    this.prepareFields();

    this.socialMedia = JSON.parse(this.appService.getSocialMedia()).socialMedia || [];
  }

  prepareFields(): void {
    this.navigation = new Navigation(this.appService.getNavigation(this.url), this.url) || null;
    const ENDPOINT = this.url.replace('/', '');

    // todo: finally this should be remove - just for mocking sites. ---
    const PAGE_CONTENT_REQUEST = this.appService.getPageContent(this.url);
    if (!!PAGE_CONTENT_REQUEST) {
      try {
        const TMP = JSON.parse(PAGE_CONTENT_REQUEST);
        this.mockPageContent = TMP.sections;
        this.pageType = ('sections' in TMP) ? TMP.type : ENDPOINT;
      } catch (e) {}
      return;
    } // ---

    this.appService.apiGetRequest(ENDPOINT).subscribe(
      (res: Response) => {
        this.pageContent = res;
        if (!('sections' in this.pageContent)) {
          this.pageType = ENDPOINT;
        }
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  changeURL(URL: string): void {
    this.url = URL;
    this.router.navigate([this.url]);

    this.prepareFields();
  }
}
