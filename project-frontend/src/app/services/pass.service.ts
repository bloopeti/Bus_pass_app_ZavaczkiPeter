import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Pass} from '../model/pass';
import {IdWrapper} from '../model/idWrapper';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class PassService {

  passURL = 'http://localhost:9905/app/pass';

  constructor(private http: HttpClient) {
  }

  getAllPasses(): Observable<Pass[]> {
    return this.http.get<Pass[]>(this.passURL + '/getAll');
  }

  getPassById(id: number): Observable<Pass> {
    return this.http.get<Pass>(this.passURL + '/get/' + id.toString());
  }

  addPass(pass: Pass): Observable<string> {
    return this.http.post(this.passURL + '/add', pass, {headers, responseType: 'text'});
  }

  updatePass(pass: Pass): Observable<string> {
    // have to specify response type text if the http request returns a plain string
    return this.http.post(this.passURL + '/update', pass, {headers, responseType: 'text'});
  }

  deletePass(id: IdWrapper): Observable<string> {
    return this.http.post(this.passURL + '/delete', id, {headers, responseType: 'text'});
  }
}
