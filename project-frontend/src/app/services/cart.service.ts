import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Cart} from '../model/cart';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartURL = 'http://localhost:9905/app/cart';

  constructor(private http: HttpClient) {
  }

  addToCart(cart: Cart): Observable<string> {
    return this.http.post(this.cartURL + '/addToCart', cart, {headers, responseType: 'text'});
  }

}
