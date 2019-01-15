import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Cart} from '../model/cart';
import {IdWrapper} from '../model/idWrapper';

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

  getAllCarts(): Observable<Cart[]> {
    return this.http.get<Cart[]>(this.cartURL + '/getAll');
  }

  getCartById(id: number): Observable<Cart> {
    return this.http.get<Cart>(this.cartURL + '/get/' + id.toString());
  }

  addCart(cart: Cart): Observable<string> {
    return this.http.post(this.cartURL + '/add', cart, {headers, responseType: 'text'});
  }

  updateCart(cart: Cart): Observable<string> {
    // have to specify response type text if the http request returns a plain string
    return this.http.post(this.cartURL + '/update', cart, {headers, responseType: 'text'});
  }

  deleteCart(id: IdWrapper): Observable<string> {
    return this.http.post(this.cartURL + '/delete', id, {headers, responseType: 'text'});
  }

  addToCart(cart: Cart): Observable<string> {
    return this.http.post(this.cartURL + '/addToCart', cart, {headers, responseType: 'text'});
  }

  removeFromCart(cart: Cart): Observable<string> {
    return this.http.post(this.cartURL + '/removeFromCart', cart, {headers, responseType: 'text'});
  }

}
