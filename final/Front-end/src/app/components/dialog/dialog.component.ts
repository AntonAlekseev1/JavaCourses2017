import {Component, OnInit} from '@angular/core';
import {Dialog} from '../../entities/dialog';
import {DialogService} from './dialog.service';
import {Message} from '../../entities/message';
import {UserService} from '../user/user.service';
import {MessageService} from '../message/message.service';
import {User} from '../../entities/user';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  user: User;
  dialog: Dialog = new Dialog();
  messages: Array<Message>;
  message: Message = new Message();

  constructor(private dialogService: DialogService, private userService: UserService,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.getDialog();
  }

  getDialog() {
    this.dialogService.getById(+localStorage.getItem('dialogId')).subscribe((dialog: Dialog) => {
      this.dialog = dialog;
      this.getDialogMessages();
      this.getUser();
    });
  }

  sendMessage() {
    this.message.date = new Date();
    this.message.userName = this.user.name;
    this.message.userLastName = this.user.lastName;
    this.messageService.sendMessage(this.dialog.id, this.message).subscribe(data => {
      console.log(data);
      this.getDialogMessages();
    });
    this.message.text = null;
  }

  getDialogMessages() {
    this.messageService.getMessages(this.dialog.id).subscribe((messages: Message[]) => {
      this.messages = messages;
    });
  }

  getUser() {
    this.userService.getUserById(+localStorage.getItem('myId')).subscribe((user: User) => {
      this.user = user;
    });
  }
}
