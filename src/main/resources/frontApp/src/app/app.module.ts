import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { SocialMediaComponent } from './components/social_media/social-media.component';
import { FooterComponent } from './components/footer/footer.component';
import { SectionPageComponent } from './components/section_page/section-page.component';
import { SearchComponent } from './components/search/search.component';
import { SessionComponent } from './components/session/session.component';
import { AuthFormsComponent } from './components/auth_forms/auth-forms.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    SocialMediaComponent,
    FooterComponent,
    SectionPageComponent,
    SearchComponent,
    SessionComponent,
    AuthFormsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
