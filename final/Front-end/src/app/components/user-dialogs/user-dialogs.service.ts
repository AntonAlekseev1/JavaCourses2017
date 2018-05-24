import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class UserDialogsService {

  private url = 'http://localhost:3030/blackbox-controler/app/dialog/';

  constructor(private http: HttpClient) {
  }

  getUserDialogs(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + 'user/' + id, {headers: myHeaders});
  }

  deleteDialog(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.delete(this.url + id, {headers: myHeaders});
  }

}
