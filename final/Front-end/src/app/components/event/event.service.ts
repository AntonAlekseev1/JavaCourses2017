import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Event} from '../../entities/event';

@Injectable()
export class EventService {

  private url = 'http://localhost:3030/blackbox-controler/app/event/';

  constructor(private http: HttpClient) {
  }

  addEventToUser(event: Event, id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.put(this.url + id, event, {headers: myHeaders});
  }

  addEventToGroup(event: Event, id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.post(this.url + id, event, {headers: myHeaders});
  }

  deleteEvent(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.delete(this.url + id, {headers: myHeaders});
  }
}
