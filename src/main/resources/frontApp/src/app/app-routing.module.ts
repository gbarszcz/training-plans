import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {AuthFormsComponent} from './components/auth_forms/auth-forms.component';
import {ProfileComponent} from './components/profile/profile.component';
import {AuthGuard} from './auth.guard';
import {ExercisesComponent} from './components/exercises/exercises.component';
import {ExerciseComponent} from './components/exercise/exercise.component';
import {DeauthGuard} from './deauth.guard';
import {LogoutComponent} from './components/logout/logout.component';
import {TrainingManagerComponent} from './components/training/training-manager.component';

const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    children: [
      { path: 'register', component: AuthFormsComponent, canActivate: [DeauthGuard] },
      { path: 'login', component: AuthFormsComponent, canActivate: [DeauthGuard] },
      { path: 'logout', component: LogoutComponent, canActivate: [AuthGuard] },
      { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
      { path: 'exercises', component: ExercisesComponent },
      { path: 'exercise/:id', component: ExerciseComponent },
      { path: 'trainings-dashboard', component: TrainingManagerComponent },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
