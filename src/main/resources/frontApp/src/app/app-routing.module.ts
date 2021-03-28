import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from './app.component';
import {AuthFormsComponent} from './components/auth_forms/auth-forms.component';

const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    children: [
      { path: 'register', component: AuthFormsComponent },
      { path: 'login', component: AuthFormsComponent },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
