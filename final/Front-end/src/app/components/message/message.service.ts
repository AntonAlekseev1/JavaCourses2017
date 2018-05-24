import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Message} from '../../entities/message';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class MessageService {

  private url = 'http://localhost:3030/blackbox-controler/app/message';

  constructor(private http: HttpClient) {
  }

  getMessages(id: number): Observable<any> {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'));
    return this.http.get(this.url + '/' + id, {headers: myHeaders});
  }

  sendMessage(id: number, message: Message) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token')).set('id', id.toString());
    return this.http.post(this.url, message, {headers: myHeaders});
  }

  delete(messageId: number, dialogId: number) {
    const myHeaders = new HttpHeaders().set('Authorization', localStorage.getItem('token'))
      .set('messageId', messageId.toString()).set('dialogId', dialogId.toString());
    return this.http.delete(this.url, {headers: myHeaders});
  }
}
