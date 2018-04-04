import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable()
export class RoomService {

  private url = 'http://localhost:8080/Server-web/RoomServlet';
  constructor(private http: HttpClient) { }
  getRooms() {
    return this.http.get(this.url);
  }
  deleteRoom(id: number) {
    const urlParams = new HttpParams().set('id', id.toString());
    return this.http.delete(this.url, { params: urlParams});
  }

}
