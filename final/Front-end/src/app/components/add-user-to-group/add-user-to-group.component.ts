import {Component, OnInit} from '@angular/core';
import {AddUserToGroupService} from './add-user-to-group.service';

@Component({
  selector: 'app-add-user-to-group',
  templateUrl: './add-user-to-group.component.html',
  styleUrls: ['./add-user-to-group.component.css']
})
export class AddUserToGroupComponent implements OnInit {

  constructor(private service: AddUserToGroupService) {
  }

  ngOnInit() {
  }

  addUserToGroup(id: number) {
    this.service.addUserToGroup(id).subscribe(data => {
      console.log(data);
    });
  }

}
