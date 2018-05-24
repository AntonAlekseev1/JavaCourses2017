import {Component, OnInit} from '@angular/core';
import {User} from '../../entities/user';
import {RegistrationService} from './registration.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  errorMessage: string;
  user: User = new User();

  constructor(private registrationService: RegistrationService, private router: Router) {
  }

  ngOnInit() {
  }

  signUp() {
    this.registrationService.signUp(this.user).subscribe(data => {
      status = data.toString();
      if (status === 'OK') {
        this.router.navigate(['']);
      } else {
        this.errorMessage = data.toString();
      }
    });
  }

}
