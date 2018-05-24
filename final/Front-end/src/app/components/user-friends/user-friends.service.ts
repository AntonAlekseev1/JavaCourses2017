import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class UserFriendsService {
  private url = 'http://localhost:3030/blackbox-controler/app/user/friends';

  constructor(private http: HttpClient) {
  }

  getFriends(id: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token')).set('id', id.toString());
    return this.http.get(this.url, {headers: myHeaders});
  }

  deleteFriends(friendId: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'))
      .set('userId', localStorage.getItem('myId')).set('friendId', friendId.toString());
    return this.http.delete(this.url, {headers: myHeaders});
  }
}
