import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationComponent} from '../components/registration/registration.component';
import {SignInComponent} from '../components/sign-in/sign-in.component';
import {UserComponent} from '../components/user/user.component';
import {UserFriendsComponent} from '../components/user-friends/user-friends.component';
import {UserDialogsComponent} from '../components/user-dialogs/user-dialogs.component';
import {UserGroupsComponent} from '../components/user-groups/user-groups.component';
import {UserEventsComponent} from '../components/user-events/user-events.component';
import {UserViewComponent} from '../components/user-view/user-view.component';
import {GroupViewComponent} from '../components/group-view/group-view.component';
import {DialogComponent} from '../components/dialog/dialog.component';
import {HomeComponent} from '../components/home/home.component';
import {EditUserProfileComponent} from '../components/edit-user-profile/edit-user-profile.component';
import {CreateGroupComponent} from '../components/create-group/create-group.component';

const routes: Routes = [
  {path: 'registration', component: RegistrationComponent},
  {path: '', component: SignInComponent},
  {path: 'user', component: UserComponent},
  {path: 'friends', component: UserFriendsComponent},
  {path: 'dialogs', component: UserDialogsComponent},
  {path: 'groups', component: UserGroupsComponent},
  {path: 'events', component: UserEventsComponent},
  {path: 'user-view', component: UserViewComponent},
  {path: 'group-view', component: GroupViewComponent},
  {path: 'dialog', component: DialogComponent},
  {path: 'home', component: HomeComponent},
  {path: 'edit', component: EditUserProfileComponent},
  {path: 'createGroup', component: CreateGroupComponent}
  ];
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
