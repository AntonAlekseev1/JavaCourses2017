import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../../entities/user';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class UserService {
  private url = 'http://localhost:3030/blackbox-controler/app/user/';
  user: User;

  constructor(private http: HttpClient) {
  }

  getUserByLogin() {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + 'login', {headers: myHeaders});
  }

  getUserById(id: number): Observable<any> {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + id, {headers: myHeaders});
  }
}
