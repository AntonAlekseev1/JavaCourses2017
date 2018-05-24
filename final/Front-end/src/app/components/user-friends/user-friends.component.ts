import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {User} from '../../entities/user';
import {UserFriendsService} from './user-friends.service';
import {Router} from '@angular/router';
import {DialogService} from '../dialog/dialog.service';
import {Dialog} from '../../entities/dialog';

@Component({
  selector: 'app-user-frends',
  templateUrl: './user-friends.component.html',
  styleUrls: ['./user-friends.component.css']
})
export class UserFriendsComponent implements OnInit {

  @ViewChild('readOnlyTemplate') readOnlyTemplate: TemplateRef<any>;
  friends: Array<User>;
  user: User;
  myId: number = +localStorage.getItem('myId');
  userId: number = +localStorage.getItem('userId');

  constructor(private friendService: UserFriendsService, private router: Router, private dialogService: DialogService) {
  }

  ngOnInit() {
    this.loadFriends();
  }

  loadFriends() {
    this.friendService.getFriends(+localStorage.getItem('userId')).subscribe((data: User[]) => {
      this.friends = data;
    });
  }

  deleteFriend(user: User) {
    this.friendService.deleteFriends(user.id).subscribe(data => {
      this.loadFriends();
    });
  }

  getById(id: number) {
    localStorage.setItem('userId', id.toString());
    this.router.navigate(['user-view']);
  }

  getDialog(id: number) {
    this.dialogService.getDialogBetweenUsers(id).subscribe((dialog: Dialog) => {
      console.log(dialog);
      localStorage.setItem('dialogId', dialog.id.toString());
      this.router.navigate(['dialog']);
    });
  }

  loadTemplate() {
    return this.readOnlyTemplate;
  }

}
