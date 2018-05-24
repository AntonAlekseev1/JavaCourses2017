import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../../entities/user';

@Injectable()
export class EditUserProfileService {

  private url = 'http://localhost:3030/blackbox-controler/app/user';

  constructor(private http: HttpClient) {
  }

  update(user: User) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.post(this.url, user, {headers: myHeaders});
  }

}
