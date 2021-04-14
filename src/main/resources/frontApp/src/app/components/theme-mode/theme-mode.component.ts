import {Component} from '@angular/core';

@Component({
  selector: 'theme-mode',
  templateUrl: './theme-mode.component.html',
  styleUrls: ['./theme-mode.component.css']
})
export class ThemeModeComponent {
  storageName = 'theme-mode';
  themeMode = false;

  constructor() {
    if (this.isSessionStorage(this.storageName)) {
      this.themeMode = !!this.getFromSession(this.storageName);
      this.setThemeMode();
    }
  }

  private isSessionStorage(name: string): boolean {
    return !!this.getFromSession(name);
  }

  private getFromSession(name: string): string | null {
    return sessionStorage.getItem(name);
  }

  private setThemeMode(): void {
    const STYLES = new Map([
      ['--bs-primary', '--bs-main'],
      ['--bs-secondary', this.themeMode ? '--bs-dark' : '--bs-light'],
      ['--bs-tertiary', !this.themeMode ? '--bs-dark' : '--bs-light']
    ]);
    this.updateStyles(STYLES);
  }

  private updateStyles(styles: Map<string, string>): void {
    this.removeWCAG();
    styles.forEach((value, variable) => {
      document.documentElement.style.setProperty(variable, `var(${value})`);
    });
    sessionStorage.setItem(this.storageName, this.themeMode.toString());
  }

  changeThemeMode(): void {
    this.themeMode = !this.themeMode;
    this.setThemeMode();
  }

  private removeWCAG(): void {
    const HTML = document.getElementsByTagName('html')[0];
    const REGEX = new RegExp('^wcag$|wcag-[a-z]{0,}', 'gm');
    HTML.className = HTML.className.replace(REGEX, '');
  }
}
