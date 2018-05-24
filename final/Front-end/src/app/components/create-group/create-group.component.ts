import {Component, OnInit} from '@angular/core';
import {CreateGroupService} from './create-group.service';
import {Group} from '../../entities/group';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent implements OnInit {

  group: Group = new Group();
  errorMessage: String;

  constructor(private createGroupService: CreateGroupService, private router: Router) {
  }

  ngOnInit() {
  }

  createGroup() {
    this.group.creationDate = new Date();
    this.createGroupService.createGroup(this.group).subscribe((group: Group) => {
      localStorage.setItem('groupId', group.id.toString());
      this.router.navigate(['group-view']);
    });
  }

}
