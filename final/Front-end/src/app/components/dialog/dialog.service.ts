import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Dialog} from '../../entities/dialog';

@Injectable()
export class DialogService {

  private url = 'http://localhost:3030/blackbox-controler/app/dialog/';
  dialog: Dialog;

  constructor(private http: HttpClient) {
  }

  getById(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + id, {headers: myHeaders});
  }

  getDialogBetweenUsers(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + '/between/' + (+localStorage.getItem('myId')) + '/' + id, {headers: myHeaders});
  }
}
