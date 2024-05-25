// statistics.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {
  private apiUrl = 'http://localhost:8090/api/stats';

  constructor(private http: HttpClient) { }

  getCounts(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/counts`).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any): Observable<never> {
    console.error('An error occurred:', error);
    return throwError('Something bad happened; please try again later.');
  }
}
