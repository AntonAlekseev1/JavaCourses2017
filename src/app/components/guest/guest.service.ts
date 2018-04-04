import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import {Guest} from './guest';
import {CookieService} from 'ngx-cookie-service';

@Injectable()
export class GuestService {

  private url = 'http://localhost:8080/Server-web/GuestServelet';
  constructor(private http: HttpClient, private cookieService: CookieService) { }

  getGuests() {
    const myHeaders = new HttpHeaders().set('Authorization', this.cookieService.get('token'));
    return this.http.get(this.url, {headers: myHeaders});
  }
  createGuest(guest: Guest) {
    const myHeaders = new HttpHeaders().set('Authorization', this.cookieService.get('token'));
    return this.http.put(this.url, {'name': guest.name, 'lastName': guest.lastName}, {headers: myHeaders});
  }
  updateGuest(id: number, guest: Guest) {
    const myHeaders = new HttpHeaders().set('Authorization', this.cookieService.get('token')).set('id', id.toString());
    return this.http.post(this.url, guest, {headers: myHeaders});
  }
  deleteGuest(id: number) {
    const urlParams = new HttpParams().set('id', id.toString());
    const myHeaders = new HttpHeaders().set('Authorization', this.cookieService.get('token')).set('id', id.toString());
    return this.http.delete(this.url, {headers: myHeaders});
  }
}
