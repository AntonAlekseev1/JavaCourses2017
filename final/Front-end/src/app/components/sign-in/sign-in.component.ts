import {Component, OnInit} from '@angular/core';
import {User} from '../../entities/user';
import {SignInService} from './sign-in.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css'],
  providers: [SignInService]
})
export class SignInComponent implements OnInit {

  errorMessage: string;
  title = 'Black Box';
  user: User;
  token: string;

  constructor(private service: SignInService, private router: Router) {
    this.user = new User();
  }

  ngOnInit() {
  }

  signIn() {
    this.service.signIn(this.user).subscribe(data => {
      if (data.toString() !== 'ERROR') {
        this.token = data.toString();
        localStorage.setItem('token', this.token);
        this.router.navigate(['user']);
      } else {
        this.errorMessage = 'invalid login or password';
      }
    });
  }

  signUp() {
    this.router.navigate(['/registration']);
  }

}
