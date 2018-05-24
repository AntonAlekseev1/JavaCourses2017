import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Group} from '../../entities/group';

@Injectable()
export class CreateGroupService {

  private url = 'http://localhost:3030/blackbox-controler/app/group';

  constructor(private http: HttpClient) {
  }

  createGroup(group: Group) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'))
      .set('id', localStorage.getItem('myId'));
    return this.http.put(this.url, group, {headers: myHeaders});
  }

}
