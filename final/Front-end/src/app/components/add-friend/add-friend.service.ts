import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class AddFriendService {
  private url = 'http://localhost:3030/blackbox-controler/app/user/friends';

  constructor(private http: HttpClient) {
  }

  addFriend(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    const body = [+localStorage.getItem('myId'), id];
    return this.http.post(this.url, body, {headers: myHeaders});
  }

}
