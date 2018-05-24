import {Component, OnInit} from '@angular/core';
import {AddFriendService} from './add-friend.service';

@Component({
  selector: 'app-add-friend',
  templateUrl: './add-friend.component.html',
  styleUrls: ['./add-friend.component.css']
})
export class AddFriendComponent implements OnInit {

  constructor(private addFriendService: AddFriendService) {
  }

  ngOnInit() {
  }

  addFriend(id: number) {
    this.addFriendService.addFriend(id).subscribe(data => {
      console.log(data);
    });
  }

}
