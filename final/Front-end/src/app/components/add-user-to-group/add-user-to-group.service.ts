import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class AddUserToGroupService {

  private url = 'http://localhost:3030/blackbox-controler/app/group/user';

  constructor(private http: HttpClient) {
  }

  addUserToGroup(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    const body = [+localStorage.getItem('myId'), id];
    return this.http.post(this.url, body, {headers: myHeaders});
  }

}
