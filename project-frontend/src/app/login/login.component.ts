import {Component, OnInit} from '@angular/core';
import {LoginService} from '../services/login.service';
import {User} from '../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userToLogin = new User();
  returnedUser = new User();
  isUserLogged: string;
  isUserAdmin: string;

  constructor(private loginService: LoginService) {
  }

  ngOnInit() {
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
  }

  attemptLogin(): void {
    this.userToLogin.emailAddress = this.userToLogin.emailAddress.trim();
    this.userToLogin.password = this.userToLogin.password.trim();
    this.loginService.attemptLogin(this.userToLogin).subscribe(data => {
      this.returnedUser = data;
      if (this.returnedUser.isAdmin === 0) {
        localStorage.setItem('isUserLoggedIn', 'true');
        localStorage.setItem('isUserAdmin', 'false');
        localStorage.setItem('loggedInUser', JSON.stringify(data));
      } else if (this.returnedUser.isAdmin === 1) {
        localStorage.setItem('isUserLoggedIn', 'true');
        localStorage.setItem('isUserAdmin', 'true');
        localStorage.setItem('loggedInUser', JSON.stringify(data));
      }
      this.isUserLogged = localStorage.getItem('isUserLoggedIn');
      this.isUserAdmin = localStorage.getItem('isUserAdmin');
    });
  }

  logout(): void {
    localStorage.setItem('isUserLoggedIn', 'false');
    localStorage.setItem('isUserAdmin', 'false');
    localStorage.setItem('loggedInUser', '');

    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
  }

}
