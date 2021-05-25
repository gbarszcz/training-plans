import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './components/profile/profile.component';
import { AuthGuard } from './auth.guard';
import { ExercisesComponent } from './components/exercises/exercises.component';
import { ExerciseComponent } from './components/exercise/exercise.component';
import { DeauthGuard } from './deauth.guard';
import { LogoutComponent } from './components/logout/logout.component';
import { TrainingManagerComponent } from './components/trainings-components/training-manager/training-manager.component';
import { SectionPageComponent } from './components/section_page/section-page.component';
import { MeasurementComponent } from './components/measurement/measurement.component';
import {TrainingPageComponent} from './components/trainings-components/training-page/training-page.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {TrainingHistoryComponent} from './components/training-history/training-history.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: SectionPageComponent },
  { path: 'register', component: SectionPageComponent, canActivate: [DeauthGuard] },
  { path: 'login', component: SectionPageComponent, canActivate: [DeauthGuard] },
  { path: 'logout', component: LogoutComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'exercises', component: ExercisesComponent },
  { path: 'exercise/:id', component: ExerciseComponent },
  { path: 'trainings-dashboard', component: TrainingManagerComponent, canActivate: [AuthGuard] },
  { path: 'training/:id', component: TrainingPageComponent, canActivate: [AuthGuard] },
  { path: 'measurement', component: MeasurementComponent, canActivate: [AuthGuard] },
  { path: 'statistics', component: StatisticsComponent, canActivate: [AuthGuard] },
  { path: '404', component: NotFoundComponent },
  { path: '**', redirectTo: '/404' },
  {path: 'trainings-history', component: TrainingHistoryComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
