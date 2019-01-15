import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class UserService {

  busURL = 'http://localhost:9905/app/user';

  constructor(private http: HttpClient) { }

  notifyAll(): Observable<any> {
    return this.http.post(this.busURL + '/notifyAll', {headers, responseType: 'text'});
  }

}
