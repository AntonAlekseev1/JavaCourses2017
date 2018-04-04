import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {GuestService} from './guest.service';
import {Guest} from './guest';

@Component({
  selector: 'app-guest',
  templateUrl: './guest.component.html',
  styleUrls: ['./guest.component.css'],
  providers: [GuestService]
})
export class GuestComponent implements OnInit {

  @ViewChild('readOnlyTemplate') readOnlyTemplate: TemplateRef<any>;
  @ViewChild('editTemplate') editTemplate: TemplateRef<any>;

  editedGuest: Guest;
  guests: Array<Guest>;
  isNewRecord: boolean;
  statusMessage: string;

  constructor(private serv: GuestService) {
    this.guests = new Array<Guest>();
  }

  ngOnInit() {
    this.loadGuests();
  }
  private loadGuests() {
    this.serv.getGuests().subscribe((data: Guest[]) => {
      this.guests = data;
    });
  }
  addGuest() {
    this.editedGuest = new Guest(0, '', '');
    this.guests.push(this.editedGuest);
    this.isNewRecord = true;
  }
  editGuest(guest: Guest) {
    this.editedGuest = new Guest(guest.id, guest.name, guest.lastName);
  }
  loadTemplate(guest: Guest) {
    if (this.editedGuest && this.editedGuest.id === guest.id) {
      return this.editTemplate;
    } else {
      return this.readOnlyTemplate;
    }
  }
  saveGuest() {
    if (this.isNewRecord) {

      this.serv.createGuest(this.editedGuest).subscribe(data => {
        this.statusMessage = 'Данные успешно добавлены';
          this.loadGuests();
      });
      this.isNewRecord = false;
      this.editedGuest = null;
    } else {
      this.serv.updateGuest(this.editedGuest.id, this.editedGuest).subscribe(data => {
        this.statusMessage = 'Данные успешно обновлены',
          this.loadGuests();
      });
      this.editedGuest = null;
    }
  }
  cancel() {
    if (this.isNewRecord) {
      this.guests.pop();
      this.isNewRecord = false;
    }
    this.editedGuest = null;
  }

  deleteGuest(guest: Guest) {
    this.serv.deleteGuest(guest.id).subscribe(data => {
      this.statusMessage = 'Данные успешно удалены';
        this.loadGuests();
    });
  }

}
