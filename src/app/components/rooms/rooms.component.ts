import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {RoomService} from './room.service';
import {Room} from './room';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css'],
  providers: [RoomService]
})
export class RoomsComponent implements OnInit {

  @ViewChild('readOnlyTemplate') readOnlyTemplate: TemplateRef<any>;
  @ViewChild('editTemplate') editTemplate: TemplateRef<any>;
  rooms: Room[];
  editedRoom: Room;
  isNewRecord: boolean;
  statusMessage: string;

  constructor(private serv: RoomService) {
    this.rooms = new Array<Room>();
  }

  ngOnInit() {
    this.loadRooms();
  }

  private loadRooms() {
    this.serv.getRooms().subscribe((data: Room[]) => {
      this.rooms = data;
    });
  }
  editRoom(room: Room) {
    this.editedRoom = new Room(room.id, room.number, room.copacity, room.stars, room.price, room.isFree, room.status);
  }
  loadTemplate(room: Room) {
    if (this.editedRoom && this.editedRoom.id === room.id) {
      return this.editTemplate;
    } else {
      return this.readOnlyTemplate;
    }
  }
  deleteRoom(room: Room) {
    this.serv.deleteRoom(room.id).subscribe(data => {
      this.statusMessage = 'Данные успешно удалены',
        this.loadRooms();
    });
  }
}
