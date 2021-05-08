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
  private endpoints = new Map([
    ['', ''],
    ['login', 'login'],
    ['logout', 'logout'],
    ['profile', 'profile'],
    ['register', 'register'],
    ['exercise', 'exercise'],
    ['exercises', 'exercises'],
    ['trainings', 'account/trainings-plans'],
  ]);
  navigation: Navigation | null = null;
  socialMedia: ISocialMedia[] = [];
  mockPageContent: IPageContent[] = [];
  pageContent: Response | null = null;
  pageType = 'section';
  url = '';

  constructor(
    private appService: AppService,
    private location: Location,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.prepareUrls();
    this.socialMedia = JSON.parse(this.appService.getSocialMedia()).socialMedia || [];
    this.prepareFields();
  }

  prepareUrls(): void {
    this.url = this.location.path() || '';
    if (!!this.url) {
      this.url = this.url.slice(1);
      this.pageType = this.url.split('/')[0];                 // @ts-ignore
      this.url = this.url.replace(this.pageType, this.endpoints.get(this.pageType));
    }
  }

  prepareFields(): void {
    this.navigation = new Navigation(this.appService.getNavigation(this.url), this.url) || null;

    // todo: finally this should be remove - just for mocking sites. ---
    const PAGE_CONTENT_REQUEST = this.appService.getPageContent(this.url);
    if (!!PAGE_CONTENT_REQUEST) {
      try {
        const TMP = JSON.parse(PAGE_CONTENT_REQUEST);
        this.mockPageContent = TMP.sections;
        this.pageType = ('sections' in TMP) ? TMP.type : this.url.split('/')[0];
      } catch (e) {}
      return;
    } // ---

    this.appService.apiGetRequest(this.url).subscribe(
      (res: Response) => {
        this.pageContent = res;
        this.prepareUrls();
        if ('sections' in this.pageContent) {
          this.pageType = 'sections';
        }
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  changeURL(URL: string): void {
    this.url = URL.charAt(0) === '/' ? URL.slice(1) : URL;
    this.router.navigate([URL]);

    this.prepareFields();
  }
}
