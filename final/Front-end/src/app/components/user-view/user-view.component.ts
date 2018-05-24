import {Component, OnInit} from '@angular/core';
import {UserService} from '../user/user.service';
import {User} from '../../entities/user';
import {UserEventsService} from '../user-events/user-events.service';
import {Event} from '../../entities/event';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit {

  user: User = new User();
  events: Array<Event>;

  constructor(private userService: UserService, private eventService: UserEventsService) {
  }

  ngOnInit() {
    this.getById();
  }

  getById() {
    this.userService.getUserById(+localStorage.getItem('userId')).subscribe((user: User) => {
      this.user = user;
      localStorage.setItem('userId', user.id.toString());
      this.getEvents();
    });
  }

  getEvents() {
    this.eventService.getEvents(this.user.id).subscribe((events: Event[]) => {
      this.events = events;
    });
  }
}
