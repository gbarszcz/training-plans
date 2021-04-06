import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-wcag',
  templateUrl: './wcag.component.html',
  styleUrls: ['./wcag.component.css']
})
export class WcagComponent {
  wcagItems = [
    {
      text: 'A+',
      type: 'font',
      variant: 'increase-xl',
      active: false
    },
    {
      text: 'A++',
      type: 'font',
      variant: 'increase-lg',
      active: false
    },
    {
      text: 'Aa',
      type: 'color',
      variant: 'dark',
      active: false
    },
    {
      text: 'Aa',
      type: 'color',
      variant: 'light',
      active: false
    }
  ];

  constructor() {
    if (this.isSessionStorageWcagSet()) {
      this.setWCAG();
    }
  }

  wcagChange(index: number): void {
    const ACTIVE_ITEM = this.wcagItems.filter(item => {
      return item.type === this.wcagItems[index].type && item.active;
    })[0];

    if (ACTIVE_ITEM && ACTIVE_ITEM !== this.wcagItems[index]) {
      ACTIVE_ITEM.active = false;
    }
    this.updateWCAG(this.wcagItems[index]);
    this.wcagItems[index].active = !this.wcagItems[index].active;
  }

  private updateWCAG(wcagItem: any): void {
    let styles;
    if (wcagItem.type === 'color') {
      styles = new Map([
        ['--bs-primary', wcagItem.variant === 'dark' ? '--bs-yellow' : '--bs-dark'],
        ['--bs-secondary', wcagItem.variant === 'dark' ? '--bs-dark' : '--bs-yellow'],
        ['--bs-tertiary', '--bs-light']
      ]);
    } else {
      styles = new Map([
        ['--font-size', `--font-size${wcagItem.variant.replace('increase', '')}`]
      ]);
    }
    this.setStyles(styles, wcagItem.active, wcagItem.variant);
  }

  private setStyles(styles: Map<string, string>, reset: boolean, variant: string): void {
    if (reset) {
      this.removeStyles(styles);
    } else {
      this.updateStyles(styles, variant);
    }
  }

  private removeStyles(styles: Map<string, string>): void {
    const HTML = document.getElementsByTagName('html')[0];
    styles.forEach((value, variable) => {
      document.documentElement.style.removeProperty(variable);
    });
    if (!this.isSessionStorageWcagSet('color') && !this.isSessionStorageWcagSet('font')) {
      sessionStorage.removeItem('wcag');
    }
    if (styles.size > 1) {
      sessionStorage.removeItem('color');
      HTML.className = '';
    } else {
      sessionStorage.removeItem('font');
    }
  }

  private updateStyles(styles: Map<string, string>, variant: string): void {
    const HTML = document.getElementsByTagName('html')[0];
    styles.forEach((value, variable) => {
      document.documentElement.style.setProperty(variable, `var(${value})`);
    });
    sessionStorage.setItem('wcag', 'true');
    if (styles.size > 1) {
      sessionStorage.setItem('color', variant || '');
      HTML.className = 'wcag';
      HTML.classList.add(variant);
    } else {
      sessionStorage.setItem('font', variant || '');
    }
  }

  private isSessionStorageWcagSet(name: string = 'wcag'): boolean {
    return !!this.getFromSession(name);
  }

  private getFromSession(name: string): string | null {
    return sessionStorage.getItem(name);
  }

  private setWCAG(): void {
    ['color', 'font'].forEach(type => {
      if (this.isSessionStorageWcagSet(type)) {
        this.wcagChange(this.wcagItems.findIndex(item => {
          return item.variant === this.getFromSession(type);
        }));
      }
    });
  }
}
