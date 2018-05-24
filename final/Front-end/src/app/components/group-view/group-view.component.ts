import {Component, OnInit} from '@angular/core';
import {Group} from '../../entities/group';
import {Event} from '../../entities/event';
import {UserGroupsService} from '../user-groups/user-groups.service';
import {User} from '../../entities/user';
import {Router} from '@angular/router';
import {EventService} from '../event/event.service';
import {GroupUsersService} from './group-users.service';
import {GroupEventsService} from './group-events.service';
import {AddUserToGroupComponent} from '../add-user-to-group/add-user-to-group.component';
import {UserFriendsComponent} from '../user-friends/user-friends.component';

@Component({
  selector: 'app-group-view',
  templateUrl: './group-view.component.html',
  styleUrls: ['./group-view.component.css']
})
export class GroupViewComponent implements OnInit {

  group: Group;
  events: Array<Event>;
  event: Event = new Event();
  users: Array<User>;
  admin: User = new User();

  constructor(private userGroupService: UserGroupsService,
              private router: Router, private eventService: EventService,
              private groupUsersService: GroupUsersService, private groupEventsService: GroupEventsService,
              private addUserToGroup: AddUserToGroupComponent, private userFriendsComponent: UserFriendsComponent) {
  }

  ngOnInit() {
    this.getGroup();
  }

  getGroup() {
    this.userGroupService.getById(+localStorage.getItem('groupId')).subscribe((group: Group) => {
      this.group = group;
      this.getGroupEvents();
      this.getGroupUsers();
      this.getGroupAdmin();
    });
  }

  getGroupAdmin() {
    this.groupUsersService.getGroupAdmin(this.group.id).subscribe((admin: User) => {
      this.admin = admin;
    });
  }

  viewGroupAdmin() {
    localStorage.setItem('userId', this.admin.id.toString());
    this.router.navigate(['user-view']);
  }

  getGroupEvents() {
    this.groupEventsService.getGroupEvents(this.group.id).subscribe((events: Event[]) => {
      this.events = events;
    });
  }

  getGroupUsers() {
    this.groupUsersService.getGroupUsers(this.group.id).subscribe((users: User[]) => {
      this.users = users;
    });
  }

  addEvent() {
    this.event.date = new Date();
    this.eventService.addEventToGroup(this.event, this.group.id).subscribe(data => {
      console.log(data);
      this.getGroupEvents();
    });
    this.event.name = null;
    this.event.body = null;
  }

  subscribe(id: number) {
    this.addUserToGroup.addUserToGroup(id);
  }

  getById(id: number) {
    this.userFriendsComponent.getById(id);
  }

  deleteEvent(id: number) {
    this.eventService.deleteEvent(id).subscribe(data => {
      this.getGroupEvents();
    });
  }

}
