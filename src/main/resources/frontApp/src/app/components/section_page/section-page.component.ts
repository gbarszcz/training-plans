import {Component, Input, OnDestroy} from '@angular/core';

import {IPageContent} from '../../models/IPageContent';

@Component({
  selector: 'section-page',
  templateUrl: './section-page.component.html'
})

export class SectionPageComponent implements OnDestroy{
  @Input() sections: IPageContent[] = [];

  ngOnDestroy(): void {
    this.sections = [];
  }
}
