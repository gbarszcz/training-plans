import {Component} from '@angular/core';

import {AppService} from '../../app.service';
import {Location} from '@angular/common';
import {IPageContent} from '../../models/IPageContent';

@Component({
  selector: 'section-page',
  templateUrl: './section-page.component.html'
})

export class SectionPageComponent {
  sections: IPageContent[] = [];
  endpoint = '/';

  constructor(private appService: AppService, private location: Location) {
    this.prepareFields();
  }

  private prepareFields(): void {
    this.endpoint = this.location.path() || '/';
    // todo: finally this should be removed - just for mocking sites. ---
    const PAGE_CONTENT_REQUEST = this.appService.getPageContent(this.endpoint);
    if (!!PAGE_CONTENT_REQUEST) {
      try {
        const TMP = JSON.parse(PAGE_CONTENT_REQUEST);
        this.sections = TMP.sections;
      } catch (e) {
        console.error(e.message);
      }
      return;
    } // ---
    this.appService.apiGetRequest(this.endpoint).subscribe(
      (res: any) => {
        this.sections = res.sections;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
}
