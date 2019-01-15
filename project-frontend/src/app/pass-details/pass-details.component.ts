import {Component, Input, OnInit} from '@angular/core';
import {Pass} from '../model/pass';
import {ActivatedRoute} from '@angular/router';
import {PassService} from '../services/pass.service';
import {IdWrapper} from '../model/idWrapper';
import {Cart} from '../model/cart';
import {CartService} from '../services/cart.service';

@Component({
  selector: 'app-pass-details',
  templateUrl: './pass-details.component.html',
  styleUrls: ['./pass-details.component.css']
})
export class PassDetailsComponent implements OnInit {

  @Input() pass = new Pass();
  id: number;
  isUserAdmin: string;
  isUserLogged: string;
  private sub: any;

  constructor(private route: ActivatedRoute, private cartService: CartService, private passService: PassService,) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.passService.getPassById(this.id)
        .subscribe(data => {
          this.pass = data;
        });
    });

    this.isUserAdmin = localStorage.getItem('isUserAdmin');
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
  }

  add() {
    const passToAdd = new Pass();
    passToAdd.type = this.pass.type.trim();
    passToAdd.price = this.pass.price;
    if (!this.pass.type) {
      return;
    }
    if (!this.pass.price) {
      return;
    }
    this.passService.addPass(passToAdd).subscribe();
  }

  update() {
    this.pass.type = this.pass.type.trim();
    this.passService.updatePass(this.pass).subscribe();
  }

  delete() {
    const idToDelete = new IdWrapper();
    idToDelete.id = this.pass.id;
    this.passService.deletePass(idToDelete).subscribe();
  }

  addToCart() {
    const cart = new Cart();
    const loggedUser = JSON.parse(localStorage.getItem('loggedInUser'));
    cart.id = loggedUser.cart.id;
    const passToAdd = new Pass();
    passToAdd.id = this.pass.id;
    cart.passes = [passToAdd];
    this.cartService.addToCart(cart).subscribe();
  }

}
