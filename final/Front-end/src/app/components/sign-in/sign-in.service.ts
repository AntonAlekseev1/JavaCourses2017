import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../../entities/user';

@Injectable()
export class SignInService {

  private url = 'http://localhost:3030/blackbox-controler/app/login';

  constructor(private http: HttpClient) {
  }

  signIn(user: User) {
    const body = {login: user.login, password: user.password};
    return this.http.post(this.url, body);
  }
}
