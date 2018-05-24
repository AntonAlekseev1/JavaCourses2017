import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class UsersSearchService {

  private url = 'http://localhost:3030/blackbox-controler/app/user';

  constructor(private http: HttpClient) {
  }

  searchUsers(name: string) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token')).set('name', name);
    return this.http.get(this.url + '/search', {headers: myHeaders});
  }

}
