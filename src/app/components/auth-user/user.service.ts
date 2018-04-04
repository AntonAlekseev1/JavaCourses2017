import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './user';
import {CookieService} from 'ngx-cookie-service';

@Injectable()
export class UserService {

  private url = 'http://localhost:8080/Server-web/LoginServlet';
  constructor(private http: HttpClient, private cookieService: CookieService) { }
  signIn(user: User) {
    const body = {login: user.login, password: user.password};
    return this.http.post(this.url, body);
  }
  signOut() {
    const myHeaders = new HttpHeaders().set('Authorization', this.cookieService.get('token'));
    return this.http.delete(this.url, {headers: myHeaders});
  }

}
