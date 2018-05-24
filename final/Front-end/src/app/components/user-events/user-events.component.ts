import {Component, OnInit} from '@angular/core';
import {User} from '../../entities/user';
import {UserEventsService} from './user-events.service';
import {Event} from '../../entities/event';
import {EventService} from '../event/event.service';

@Component({
  selector: 'app-user-events',
  templateUrl: './user-events.component.html',
  styleUrls: ['./user-events.component.css']
})
export class UserEventsComponent implements OnInit {

  user: User;
  events: Array<Event>;

  constructor(private userEventService: UserEventsService, private eventService: EventService) {
  }

  ngOnInit() {
    this.loadEvents();
  }

  loadEvents() {
    this.userEventService.getEvents(+localStorage.getItem('userId')).subscribe((data: Event[]) => {
      this.events = data;
      console.log(this.events);
    });
  }

  deleteEvent(id: number) {
    this.eventService.deleteEvent(id).subscribe(data => {
      this.loadEvents();
    });
  }
}
