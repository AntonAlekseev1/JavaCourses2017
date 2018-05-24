import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class LogOutService {

  private url = 'http://localhost:3030/blackbox-controler/app/login';

  constructor(private http: HttpClient) {
  }

  logOut() {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.delete(this.url, {headers: myHeaders});
  }

}
