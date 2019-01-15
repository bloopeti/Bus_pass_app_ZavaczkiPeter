import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Zavaczki Peter\'s DS Project';
  isUserLogged: string;
  isUserAdmin: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
  }

  notifyUsersOfExpiredPasses(): void {
    this.userService.notifyAll().subscribe();
  }

}
