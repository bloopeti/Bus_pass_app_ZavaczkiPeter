import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Bus} from '../model/bus';
import {ErrorHandlerService} from './error-handler.service';

@Injectable({
  providedIn: 'root'
})
export class BusService {

  busURL = 'http://localhost:9905/app/bus';

  constructor(private http: HttpClient, private errorHandlerService: ErrorHandlerService) {
  }

  getAllBuses(): Observable<Bus[]> {
    return this.http.get<Bus[]>(this.busURL + '/getAll');
  }

  getBusById(id: number): Observable<Bus> {
    return this.http.get<Bus>(this.busURL + '/get/${id}');
  }
}
