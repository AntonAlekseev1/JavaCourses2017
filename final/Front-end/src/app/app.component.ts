import {Component} from '@angular/core';
import {LogOutService} from './components/log-out/log-out.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private service: LogOutService, private router: Router) {
  }

  logOut() {
    this.service.logOut().subscribe(data => {
      console.log(data);
    });
    localStorage.removeItem('token');
    localStorage.removeItem('myId');
    localStorage.removeItem('userId');
    this.router.navigate(['']);
  }
}
