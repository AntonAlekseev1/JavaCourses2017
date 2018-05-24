import {Component, OnInit} from '@angular/core';
import {EditUserProfileService} from './edit-user-profile.service';
import {User} from '../../entities/user';
import {UserService} from '../user/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-edit-user-profile',
  templateUrl: './edit-user-profile.component.html',
  styleUrls: ['./edit-user-profile.component.css']
})
export class EditUserProfileComponent implements OnInit {

  user: User = new User();

  constructor(private editProfile: EditUserProfileService, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.getUser();
  }

  update() {
    this.editProfile.update(this.user).subscribe(data => {
      status = data.toString();
      if (status === 'OK') {
        this.router.navigate(['user']);
      } else {
        alert('error');
      }
    });
  }

  getUser() {
    this.userService.getUserByLogin().subscribe((user: User) => {
      this.user = user;
    });
  }

}
