import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from './app.component';
import {SessionComponent} from './components/session/session.component';

const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    children: [
      { path: 'register', component: SessionComponent },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
