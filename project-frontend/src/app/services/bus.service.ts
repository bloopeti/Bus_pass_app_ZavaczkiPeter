import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ErrorHandlerService} from './error-handler.service';
import {Bus} from '../model/bus';
import {IdWrapper} from '../model/idWrapper';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class BusService {

  busURL = 'http://localhost:9905/app/bus';

  constructor(private http: HttpClient) {
  }

  getAllBuses(): Observable<Bus[]> {
    return this.http.get<Bus[]>(this.busURL + '/getAll');
  }

  getBusById(id: number): Observable<Bus> {
    return this.http.get<Bus>(this.busURL + '/get/' + id.toString());
  }

  // Bad implementation if http request returns string. We need to define response type as text
  // addBus(bus: Bus): Observable<string> {
  //   return this.http.post<string>(this.busURL + '/add', bus, httpOptions);
  // }
  addBus(bus: Bus): Observable<string> {
    return this.http.post(this.busURL + '/add', bus, {headers, responseType: 'text'});
  }

  updateBus(bus: Bus): Observable<string> {
    // have to specify response type text if the http request returns a plain string
    return this.http.post(this.busURL + '/update', bus, {headers, responseType: 'text'});
  }

  deleteBus(id: IdWrapper): Observable<string> {
    return this.http.post(this.busURL + '/delete', id, {headers, responseType: 'text'});
  }
}
