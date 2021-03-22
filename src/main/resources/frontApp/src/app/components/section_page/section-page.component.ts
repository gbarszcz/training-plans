import {Component, Input, OnDestroy} from '@angular/core';

import {IPageSection} from '../../models/IPageSection';

@Component({
  selector: 'section-page',
  templateUrl: './section-page.component.html'
})

export class SectionPageComponent implements OnDestroy{
  @Input() sections: IPageSection[] = [];

  ngOnDestroy(): void {
    this.sections = [];
  }
}
