import {Component, EventEmitter, Input, OnDestroy, Output} from '@angular/core';

import {IPageContent} from '../../models/IPageContent';

@Component({
  selector: 'section-page',
  templateUrl: './section-page.component.html'
})

export class SectionPageComponent implements OnDestroy{
  @Output() URLEvent = new EventEmitter<string>();
  @Input() sections: IPageContent[] = [];

  ngOnDestroy(): void {
    this.sections = [];
  }

  changeURL(URL: string): void {
    this.URLEvent.emit(URL);
  }
}
