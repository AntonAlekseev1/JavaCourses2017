import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {UserService} from './user.service';
import {User} from './user';
import {CookieService} from 'ngx-cookie-service';

@Component({
  selector: 'app-auth-user',
  templateUrl: './auth-user.component.html',
  styleUrls: ['./auth-user.component.css'],
  providers: [UserService]
})
export class AuthUserComponent implements OnInit {
  @ViewChild('editTemplate') editTemplate: TemplateRef<any>;

  user: User;
  token: string;
  statusMessage: string;
  constructor(private serv: UserService, private cookieService: CookieService) {
    this.user = new User('', '');
  }

  ngOnInit() {
  }
  signIn() {
    this.serv.signIn(this.user).subscribe(data => {
      this.token = data.toString();
      this.cookieService.set('token', this.token);
      this.statusMessage = 'Добро пожаловать';
    });
  }
  signOut() {
    this.serv.signOut().subscribe();
  }

}
