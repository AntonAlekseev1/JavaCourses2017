import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Group} from '../../entities/group';

@Injectable()
export class UserGroupsService {

  private url = 'http://localhost:3030/blackbox-controler/app/group/';
  group: Group;

  constructor(private http: HttpClient) {
  }

  getGroups(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token')).set('id', id.toString());
    return this.http.get(this.url + 'user', {headers: myHeaders});
  }

  getById(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + id, {headers: myHeaders});
  }

  removeGroup(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'))
      .set('groupId', id.toString()).set('userId', localStorage.getItem('myId'));
    return this.http.delete(this.url + 'user', {headers: myHeaders});
  }
}
