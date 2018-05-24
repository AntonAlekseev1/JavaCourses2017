import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {User} from '../../entities/user';
import {UserGroupsService} from './user-groups.service';
import {Group} from '../../entities/group';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-groups',
  templateUrl: './user-groups.component.html',
  styleUrls: ['./user-groups.component.css']
})
export class UserGroupsComponent implements OnInit {

  @ViewChild('readOnlyTemplate') readOnlyTemplate: TemplateRef<any>;
  user: User;
  groups: Array<Group>;
  myId: number = +localStorage.getItem('myId');
  userId: number = +localStorage.getItem('userId');

  constructor(private groupService: UserGroupsService, private router: Router) {
  }

  ngOnInit() {
    this.loadGroups();
  }

  loadGroups() {
    this.groupService.getGroups(+localStorage.getItem('userId')).subscribe((data: Group[]) => {
      this.groups = data;
    });
  }

  getById(id: number) {
    localStorage.setItem('groupId', id.toString());
    this.router.navigate(['group-view']);
  }

  removeGroup(group: Group) {
    this.groupService.removeGroup(group.id).subscribe(data => {
      this.loadGroups();
    });
  }

  loadTemplate() {
    return this.readOnlyTemplate;
  }

}
