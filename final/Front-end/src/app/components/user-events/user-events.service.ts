import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class UserEventsService {
  private url = 'http://localhost:3030/blackbox-controler/app/event/user/';

  constructor(private http: HttpClient) {
  }

  getEvents(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + id, {headers: myHeaders});
  }
}
