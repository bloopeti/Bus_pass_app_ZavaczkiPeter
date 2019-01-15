import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Pass} from '../model/pass';

@Injectable({
  providedIn: 'root'
})
export class PassService {

  passURL = 'http://localhost:9905/app/pass';

  constructor(private http: HttpClient) {
  }

  getAllPasses(): Observable<Pass[]>  {
    return this.http.get<Pass[]>(this.passURL + '/getAll');
  }
}
