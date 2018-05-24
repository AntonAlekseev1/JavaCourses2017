import {Component, OnInit} from '@angular/core';
import {UsersSearchService} from './users-search.service';
import {User} from '../../entities/user';
import {UserFriendsComponent} from '../user-friends/user-friends.component';

@Component({
  selector: 'app-users-search',
  templateUrl: './users-search.component.html',
  styleUrls: ['./users-search.component.css']
})
export class UsersSearchComponent implements OnInit {

  name: string;
  users: Array<User>;

  constructor(private  searchService: UsersSearchService, private userFriendsComponent: UserFriendsComponent) {
  }

  ngOnInit() {
  }

  searchUsers() {
    this.searchService.searchUsers(this.name).subscribe((users: User[]) => {
      this.users = users;
    });
  }

  getById(id: number) {
    this.userFriendsComponent.getById(id);
  }

}
