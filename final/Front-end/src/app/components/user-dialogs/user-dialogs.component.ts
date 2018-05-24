import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {UserDialogsService} from './user-dialogs.service';
import {Dialog} from '../../entities/dialog';
import {UserService} from '../user/user.service';
import {User} from '../../entities/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-dialogs',
  templateUrl: './user-dialogs.component.html',
  styleUrls: ['./user-dialogs.component.css']
})
export class UserDialogsComponent implements OnInit {

  @ViewChild('readOnlyTemplate') readOnlyTemplate: TemplateRef<any>;
  dialogs: Array<Dialog>;
  dialog: Dialog = new Dialog();
  user: User;

  constructor(private userDialogsService: UserDialogsService,
              private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.loadDialogs();
  }

  loadDialogs() {
    this.userDialogsService.getUserDialogs(+localStorage.getItem('userId')).subscribe((dialogs: Dialog[]) => {
      this.dialogs = dialogs;
    });
  }

  getById(id: number) {
    localStorage.setItem('dialogId', id.toString());
    this.router.navigate(['dialog']);
  }

  deleteDialog(id: number) {
    this.userDialogsService.deleteDialog(id).subscribe((data: Map<string, Object>) => {
      console.log(data);
      this.loadDialogs();
    });
  }

  loadTemplate() {
    return this.readOnlyTemplate;
  }

}
