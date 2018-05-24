import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class GroupUsersService {

  private url = 'http://localhost:3030/blackbox-controler/app/user/';

  constructor(private http: HttpClient) {
  }

  getGroupAdmin(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + 'admin/' + id, {headers: myHeaders});
  }

  getGroupUsers(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + 'group/' + id, {headers: myHeaders});
  }

}
