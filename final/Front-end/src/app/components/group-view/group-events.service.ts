import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class GroupEventsService {

  private url = 'http://localhost:3030/blackbox-controler/app/event/';
  token = localStorage.getItem('token');

  constructor(private http: HttpClient) {
  }

  getGroupEvents(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', this.token);
    return this.http.get(this.url + 'group/' + id, {headers: myHeaders});
  }

}
