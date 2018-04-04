import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import {GuestComponent} from '../components/guest/guest.component';
import {RoomsComponent} from '../components/rooms/rooms.component';
import {AuthUserComponent} from '../components/auth-user/auth-user.component';
import {AppComponent} from '../app.component';

const routes: Routes = [
  {path: 'guests', component: GuestComponent},
  {path: 'rooms', component: RoomsComponent},
  {path: 'logIn', component: AuthUserComponent},
  {path: 'hotel', component: AppComponent}
  ];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
