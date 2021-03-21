import {Component, OnInit} from '@angular/core';
import {AppService} from './app.service';
import {Navigation} from './utils/Navigation';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  navigation: Navigation | null = null;

  constructor(private appService: AppService, private router: Router) {
  }

  ngOnInit(): void {
    this.navigation = new Navigation(this.appService.getNavigation(this.router.url), this.router.url) || null;
  }
}
