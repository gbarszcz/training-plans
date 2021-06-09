import {AfterViewInit, Component, EventEmitter, Input, Output} from '@angular/core';

import {Navigation} from '../../models/Navigation';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})

export class NavigationComponent implements AfterViewInit {
  @Input() position = 'fixed';
  @Input() navigation: Navigation | null = null;

  ngAfterViewInit(): void {
    this.navEvent();
  }

  private navEvent(): void {
    this.changeSubNavActiveItem();
    window.addEventListener('scroll', () => {
      this.changeSubNavActiveItem();
    });
    // More actions can be put below
  }

  private changeSubNavActiveItem(): void {
    const NAV = document.querySelector('nav');
    if (NAV) {
      const PAGE_TOP_OFFSET = window.pageYOffset;
      const NAV_HEIGHT = NAV.offsetHeight;

      document.querySelectorAll('nav .nav_secondary li a')?.forEach((item) => {
        const TARGET_ID = item.getAttribute('href');
        if (TARGET_ID) {
          const SECTION = document.getElementById(TARGET_ID.replace('#', ''));
          if (SECTION) {
            const SECTION_START = SECTION.offsetTop - NAV_HEIGHT - window.innerHeight / 3.5;
            const SECTION_END = SECTION.offsetHeight + SECTION_START;

            if (PAGE_TOP_OFFSET >= SECTION_START
              && PAGE_TOP_OFFSET <= SECTION_END) { // Section
              if (!item.parentElement?.classList.contains('active')) {
                item.parentElement?.classList.toggle('active');
              }
            } else { // Out of section
              if (item.parentElement?.classList.contains('active')) {
                item.parentElement?.classList.toggle('active');
              }
            }
          }
        }
      });
    }
  }
}
