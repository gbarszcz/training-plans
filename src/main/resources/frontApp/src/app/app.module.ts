import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
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
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
