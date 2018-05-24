import {Component, OnInit} from '@angular/core';
import {HomeService} from './home.service';
import {User} from '../../entities/user';
import {Group} from '../../entities/group';
import {UserFriendsComponent} from '../user-friends/user-friends.component';
import {AddFriendComponent} from '../add-friend/add-friend.component';
import {UserGroupsComponent} from '../user-groups/user-groups.component';
import {AddUserToGroupComponent} from '../add-user-to-group/add-user-to-group.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  users: Array<User>;
  groups: Array<Group>;

  constructor(private homeService: HomeService, private userFriendsComponent: UserFriendsComponent,
              private addFriendComponent: AddFriendComponent, private groupComponent: UserGroupsComponent,
              private addUserToGroup: AddUserToGroupComponent) {
  }

  ngOnInit() {
  }

  getAllUsers() {
    this.homeService.getAllUsers().subscribe((users: User[]) => {
      this.users = users;
    });
  }

  getAllGroups() {
    this.homeService.getAllGroups().subscribe((groups: Group[]) => {
      this.groups = groups;
    });
  }

  getById(id: number) {
    this.userFriendsComponent.getById(id);
  }

  getGroupById(id: number) {
    this.groupComponent.getById(id);
  }

  addFriend(id: number) {
    this.addFriendComponent.addFriend(id);
  }

  subscribe(id: number) {
    this.addUserToGroup.addUserToGroup(id);
  }

}
