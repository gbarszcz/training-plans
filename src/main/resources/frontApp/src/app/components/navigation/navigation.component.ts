import {AfterViewInit, Component, Input} from '@angular/core';

import {Navigation} from '../../utils/Navigation';

@Component({
  selector: 'main-navigation',
  templateUrl: './navigation.component.html'
})

export class NavigationComponent implements AfterViewInit {
  @Input() position = 'fixed-top';
  @Input() navigation: Navigation | null = null;

  ngAfterViewInit(): void {
    this.dropdownEvent();
    this.navEvent();
  }

  private dropdownEvent(): void {
    document.querySelectorAll('.dropdown').forEach(dropdown => {
      window.addEventListener('click', e => {
        if (dropdown.contains(e.target as Node)) {
          if (!dropdown.classList.contains('show')) {
            dropdown.classList.add('show');
            dropdown.querySelector('.dropdown-menu')?.classList.add('show');
          }
        } else {
          if (dropdown.classList.contains('show')) {
            dropdown.classList.remove('show');
            dropdown.querySelector('.dropdown-menu')?.classList.remove('show');
          }
        }
      });
    });
  }

  private navEvent(): void {
    this.changeNavActiveItem();
    this.changeSubNavActiveItem();
    window.addEventListener('scroll', () => {
      this.changeSubNavActiveItem();
    });
    // More actions can be put below
  }

  private changeNavActiveItem(): void {
    const NAV = document.querySelectorAll('nav .nav_primary .navbar-nav');
    if (NAV) {
      NAV.forEach((nav) => {
        nav?.querySelectorAll('li a').forEach((item) => {
          const TARGET_ID = item.getAttribute('href');
          if (item) {
            if (TARGET_ID !== '#top') {
              if (item.parentElement?.classList.contains('active')) {
                item.parentElement?.classList.toggle('active');
              }
            } else {
              if (!item.parentElement?.classList.contains('active')) {
                item.parentElement?.classList.toggle('active');
              }
            }
          }
        });
      });
    }
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
