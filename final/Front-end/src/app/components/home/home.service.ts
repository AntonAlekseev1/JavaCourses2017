import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class HomeService {

  private url = 'http://localhost:3030/blackbox-controler/app';

  constructor(private http: HttpClient) {
  }

  getAllUsers() {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + '/user', {headers: myHeaders});
  }

  getAllGroups() {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + '/group', {headers: myHeaders});
  }

}
