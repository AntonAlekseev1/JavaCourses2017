import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../../entities/user';

@Injectable()
export class RegistrationService {

  private url = 'http://localhost:3030/blackbox-controler/app/login';

  constructor(private http: HttpClient) {
  }

  signUp(user: User) {
    return this.http.put(this.url, user);
  }
}
