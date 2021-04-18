import {Component} from '@angular/core';

@Component({
  selector: 'theme-mode',
  templateUrl: './theme-mode.component.html',
  styleUrls: ['./theme-mode.component.css']
})
export class ThemeModeComponent {
  storageName = 'theme-mode';
  themeMode = 'light';
  themes = new Map([
    ['light',
      {
        colors: new Map([
          ['--bs-primary', '--bs-main'],
          ['--bs-secondary', '--bs-light'],
          ['--bs-tertiary', '--bs-dark']
        ]),
        text: 'Light',
        icon: 'sun',
      }
    ],
    ['dark',
      {
        colors: new Map([
          ['--bs-primary', '--bs-main'],
          ['--bs-secondary', '--bs-dark'],
          ['--bs-tertiary', '--bs-light']
        ]),
        text: 'Dark',
        icon: 'moon-stars',
      }
    ]
  ]);

  constructor() {
    if (this.isSessionStorage(this.storageName)) {
      this.themeMode = this.getFromSession(this.storageName) ?? 'light';
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
    this.removeWCAG();
    // @ts-ignore
    this.themes.get(this.themeMode).colors.forEach((value, variable) => {
      document.documentElement.style.setProperty(variable, `var(${value})`);
    });
    sessionStorage.setItem(this.storageName, this.themeMode.toString());
  }

  changeThemeMode(mode: string): void {
    this.themeMode = mode;
    this.setThemeMode();
  }

  private removeWCAG(): void {
    const HTML = document.getElementsByTagName('html')[0];
    const REGEX = new RegExp('^wcag$|wcag-[a-z]{0,}', 'gm');
    HTML.className = HTML.className.replace(REGEX, '');
  }
}
