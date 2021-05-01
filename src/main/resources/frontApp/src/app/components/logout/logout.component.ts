import {Component} from '@angular/core';
import {Location} from '@angular/common';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {finalize} from 'rxjs/operators';
import {AppService} from "../../app.service";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html'
})
export class LogoutComponent {
  constructor(
    private location: Location,
    private router: Router,
    private http: HttpClient,
    private service: AppService
  ) {
    if (this.location.path() === '/logout') {
      this.logout();
    }
  }

  logout(): void {
    sessionStorage.removeItem('username');
    this.service.apiPostRequest('logout', {}).pipe(
      finalize(() => {
        window.location.href = '/login';
      })
    ).subscribe();
  }
}
