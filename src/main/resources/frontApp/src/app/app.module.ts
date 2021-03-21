import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {NavigationComponent} from './components/navigation/navigation.component';
import {SocialMediaComponent} from './components/social_media/social-media.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    SocialMediaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
