import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Bus} from '../model/bus';
import {User} from '../model/user';

const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userURL = 'http://localhost:9905/app/user';

  constructor(private http: HttpClient) {
  }

  notifyAll(): Observable<any> {
    return this.http.post(this.userURL + '/notifyAll', {headers, responseType: 'text'});
  }

  buyPassesInCart(user: User): Observable<string> {
    return this.http.post(this.userURL + '/buyPassesInCart', user, {headers, responseType: 'text'});
  }

}
