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
      variant: 'increase',
      active: false
    },
    {
      text: 'A++',
      type: 'font',
      variant: 'increase2',
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
    this.updateStyles(this.wcagItems[index]);
    this.wcagItems[index].active = !this.wcagItems[index].active;
  }

  private updateStyles(wcagItem: any): void {
    if (wcagItem.type === 'color') {
      this.updateColor(wcagItem.variant, wcagItem.active);
    } else {
      this.updateFont(wcagItem.variant, wcagItem.active);
    }
  }

  private updateColor(variant: string, reset: boolean): void {
    const STYLES = new Map([
      ['--bs-primary', variant === 'dark' ? '--bs-yellow' : '--bs-dark'],
      ['--bs-secondary', variant === 'dark' ? '--bs-dark' : '--bs-yellow'],
      ['--bs-tertiary', variant === 'dark' ? '--bs-light' : '--bs-light']
    ]);
    this.setStyles(STYLES, reset, variant);
  }

  private updateFont(variant: string, reset: boolean): void {
    const STYLES = new Map([
      ['--font-size', variant === 'increase' ? '--font-size-inc' : '--font-size-inc2']
    ]);
    this.setStyles(STYLES, reset, variant);
  }

  private setStyles(styles: Map<string, string>, reset: boolean, variant: string): void {
    const HTML = document.getElementsByTagName('html')[0];
    if (reset) {
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
    } else {
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
  }

  private isSessionStorageWcagSet(name: string = 'wcag'): boolean {
    return sessionStorage.getItem(name) !== null;
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
