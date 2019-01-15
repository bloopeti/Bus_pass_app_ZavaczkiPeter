import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  constructor(private router: Router) {
  }

  private handleError<T>(operation = 'operation', result?: T) {
    console.error('${operation} failed.'); // log to console

    this.router.navigate(['/error']);

    return of(result as T);
  }

}
