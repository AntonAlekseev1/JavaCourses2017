import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { GuestComponent } from './components/guest/guest.component';
import {AppRoutingModule} from './app-routing/app-routing.module';
import { AuthUserComponent } from './components/auth-user/auth-user.component';
import { RoomsComponent } from './components/rooms/rooms.component';
import {CookieService} from 'ngx-cookie-service';

@NgModule({
  imports:      [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    CookieService
  ],
  declarations: [
    AppComponent,
    GuestComponent,
    AuthUserComponent,
    RoomsComponent
  ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
