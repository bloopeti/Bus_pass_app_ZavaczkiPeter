import {Component, OnInit} from '@angular/core';
import {Cart} from '../model/cart';
import {CartService} from '../services/cart.service';
import {Router} from '@angular/router';
import {Pass} from '../model/pass';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-carts',
  templateUrl: './personal-cart.component.html',
  styleUrls: ['./personal-cart.component.css']
})
export class PersonalCartComponent implements OnInit {

  cart = new Cart();
  passes: Pass[];
  displayedColumns: string[] = ['type', 'price', 'remove'];
  currencyCode = 'RON';

  constructor(private router: Router, private cartService: CartService, private userService: UserService) {
  }

  ngOnInit() {
    this.getLoggedUsersCart();
    this.passes = this.cart.passes;
  }

  getLoggedUsersCart(): void {
    const loggedUser = JSON.parse(localStorage.getItem('loggedInUser'));
    this.cartService.getCartById(loggedUser.cart.id).subscribe(data => this.cart = data);
  }

  buyPassesInCart(): void {
    const loggedUser = JSON.parse(localStorage.getItem('loggedInUser'));
    this.userService.buyPassesInCart(loggedUser).subscribe();
  }

  removeFromCart(id: number): void {
    const cart = new Cart();
    const loggedUser = JSON.parse(localStorage.getItem('loggedInUser'));
    cart.id = loggedUser.cart.id;
    const passToAdd = new Pass();
    passToAdd.id = id;
    cart.passes = [passToAdd];
    this.cartService.removeFromCart(cart).subscribe();
  }
}
