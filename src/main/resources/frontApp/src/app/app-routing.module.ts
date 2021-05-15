import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProfileComponent} from './components/profile/profile.component';
import {AuthGuard} from './auth.guard';
import {ExercisesComponent} from './components/exercises/exercises.component';
import {ExerciseComponent} from './components/exercise/exercise.component';
import {DeauthGuard} from './deauth.guard';
import {LogoutComponent} from './components/logout/logout.component';
import {TrainingManagerComponent} from './components/training/training-manager.component';
import {SectionPageComponent} from './components/section_page/section-page.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', component: SectionPageComponent},
  {path: 'register', component: SectionPageComponent, canActivate: [DeauthGuard]},
  {path: 'login', component: SectionPageComponent, canActivate: [DeauthGuard]},
  {path: 'logout', component: LogoutComponent, canActivate: [AuthGuard]},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'exercises', component: ExercisesComponent},
  {path: 'exercise/:id', component: ExerciseComponent},
  {path: 'trainings', component: TrainingManagerComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
