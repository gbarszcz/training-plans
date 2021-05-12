import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { SocialMediaComponent } from './components/social_media/social-media.component';
import { FooterComponent } from './components/footer/footer.component';
import { SectionPageComponent } from './components/section_page/section-page.component';
import { SearchComponent } from './components/search/search.component';
import { AuthFormsComponent } from './components/auth_forms/auth-forms.component';
import { AlertComponent } from './components/alert/alert.component';
import { WcagComponent } from './components/wcag/wcag.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ThemeModeComponent } from './components/theme-mode/theme-mode.component';
import { LogoutComponent } from './components/logout/logout.component';
import { ExercisesComponent } from './components/exercises/exercises.component';
import { ExerciseComponent } from './components/exercise/exercise.component';
import { TrainingManagerComponent } from './components/training/training-manager.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import interactionPlugin from '@fullcalendar/interaction';
import bootstrapPlugin from '@fullcalendar/bootstrap';

FullCalendarModule.registerPlugins([ // register FullCalendar plugins
  dayGridPlugin,
  timeGridPlugin,
  listPlugin,
  interactionPlugin,
  bootstrapPlugin,
]);

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    SocialMediaComponent,
    FooterComponent,
    SectionPageComponent,
    SearchComponent,
    AuthFormsComponent,
    AlertComponent,
    WcagComponent,
    ProfileComponent,
    ThemeModeComponent,
    ExercisesComponent,
    ExerciseComponent,
    LogoutComponent,
    TrainingManagerComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    HttpClientXsrfModule,
    FullCalendarModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
