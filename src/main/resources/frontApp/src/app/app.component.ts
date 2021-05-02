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

  constructor(
    private appService: AppService,
    private location: Location,
    private router: Router
  ) {
    this.url = location.path() || '';
    if (!!this.url) {
      this.url = this.url.slice(1);
    }
  }

  ngOnInit(): void {
    this.prepareFields();
    this.socialMedia = JSON.parse(this.appService.getSocialMedia()).socialMedia || [];
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
        if (!('sections' in this.pageContent)) {
          this.pageType = this.url.split('/')[0];
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
