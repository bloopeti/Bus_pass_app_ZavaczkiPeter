import {Injectable} from '@angular/core';
import {User} from '../model/user';
import {Observable} from 'rxjs';
import {IdWrapper} from '../model/idWrapper';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  busURL = 'http://localhost:9905/app/user';

  constructor(private http: HttpClient) {
  }

  attemptLogin(user: User): Observable<User> {
    return this.http.post<User>(this.busURL + '/login', user, httpOptions);
  }
}
