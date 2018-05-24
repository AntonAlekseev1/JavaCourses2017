import {Component, OnInit} from '@angular/core';
import {User} from '../../entities/user';
import {UserService} from './user.service';
import {UserEventsService} from '../user-events/user-events.service';
import {Event} from '../../entities/event';
import {EventService} from '../event/event.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: User = new User();
  events: Array<Event>;
  event: Event = new Event();

  constructor(private service: UserService, private userEventService: UserEventsService, private eventService: EventService) {
  }

  ngOnInit() {
    this.loadUser();
  }

  loadUser() {
    this.service.getUserByLogin().subscribe((user: User) => {
      this.user = user;
      localStorage.setItem('myId', this.user.id.toString());
      localStorage.setItem('userId', this.user.id.toString());
      this.getUserEvents();
    });
  }

  getUserEvents() {
    this.userEventService.getEvents(this.user.id).subscribe((events: Event[]) => {
      this.events = events;
    });
  }

  addEvent() {
    this.event.date = new Date();
    this.eventService.addEventToUser(this.event, this.user.id).subscribe(data => {
      console.log(data);
      this.getUserEvents();
    });
    this.event.name = null;
    this.event.body = null;
  }

  deleteEvent(id: number) {
    this.eventService.deleteEvent(id).subscribe(data => {
      this.getUserEvents();
    });
  }

}
