import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';


import {AppComponent} from './app.component';
import {SignInComponent} from './components/sign-in/sign-in.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {AppRoutingModule} from './app-routing/app-routing.module';
import {UserComponent} from './components/user/user.component';
import {UserFriendsComponent} from './components/user-friends/user-friends.component';
import {UserDialogsComponent} from './components/user-dialogs/user-dialogs.component';
import {SignInService} from './components/sign-in/sign-in.service';
import {UserService} from './components/user/user.service';
import {UserGroupsComponent} from './components/user-groups/user-groups.component';
import {UserEventsComponent} from './components/user-events/user-events.component';
import {UserFriendsService} from './components/user-friends/user-friends.service';
import {UserGroupsService} from './components/user-groups/user-groups.service';
import {UserViewComponent} from './components/user-view/user-view.component';
import {UserEventsService} from './components/user-events/user-events.service';
import {RegistrationService} from './components/registration/registration.service';
import {GroupViewComponent} from './components/group-view/group-view.component';
import {DialogComponent} from './components/dialog/dialog.component';
import {DialogService} from './components/dialog/dialog.service';
import {UserDialogsService} from './components/user-dialogs/user-dialogs.service';
import {MessageService} from './components/message/message.service';
import {LogOutService} from './components/log-out/log-out.service';
import {EventService} from './components/event/event.service';
import {GroupUsersService} from './components/group-view/group-users.service';
import {GroupEventsService} from './components/group-view/group-events.service';
import {HomeComponent} from './components/home/home.component';
import {HomeService} from './components/home/home.service';
import {UsersSearchComponent} from './components/users-search/users-search.component';
import {UsersSearchService} from './components/users-search/users-search.service';
import {AddFriendComponent} from './components/add-friend/add-friend.component';
import {AddFriendService} from './components/add-friend/add-friend.service';
import {AddUserToGroupComponent} from './components/add-user-to-group/add-user-to-group.component';
import {AddUserToGroupService} from './components/add-user-to-group/add-user-to-group.service';
import {EditUserProfileComponent} from './components/edit-user-profile/edit-user-profile.component';
import {EditUserProfileService} from './components/edit-user-profile/edit-user-profile.service';
import {CreateGroupComponent} from './components/create-group/create-group.component';
import {CreateGroupService} from './components/create-group/create-group.service';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [SignInService, UserService, UserFriendsService, UserGroupsService,
    UserEventsService, RegistrationService, HomeService,
    DialogService, UserDialogsService, MessageService, LogOutService, EventService,
    GroupUsersService, GroupEventsService, UsersSearchService, UserFriendsComponent,
    AddFriendComponent, AddFriendService, UserGroupsComponent, AddUserToGroupService,
    AddUserToGroupComponent, EditUserProfileService, CreateGroupService],
  declarations: [
    AppComponent,
    SignInComponent,
    RegistrationComponent,
    UserComponent,
    UserFriendsComponent,
    UserDialogsComponent,
    UserGroupsComponent,
    UserEventsComponent,
    UserViewComponent,
    GroupViewComponent,
    DialogComponent,
    HomeComponent,
    UsersSearchComponent,
    AddFriendComponent,
    AddUserToGroupComponent,
    EditUserProfileComponent,
    CreateGroupComponent,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
