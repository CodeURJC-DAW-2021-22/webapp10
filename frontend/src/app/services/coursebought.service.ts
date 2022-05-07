import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { CourseBoughtTimes } from '../models/CourseBoughtTimes.model';
import { Order } from '../models/order.model';
import { User } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class CourseboughtService {
  constructor(private http: HttpClient) {}

  getCourseBoughtTimes(user: User): Observable<CourseBoughtTimes[]> {
    return this.http
      .get<CourseBoughtTimes[]>('/api/users/' + user.id + '/graph', {
        withCredentials: true,
      })
      .pipe(catchError(error => this.handleError(error))) as Observable<
      CourseBoughtTimes[]
    >;
  }

  getOrders(user: User): Observable<Order[]> {
    return this.http
      .get<Order[]>('/api/orders/userOrders', { withCredentials: true })
      .pipe(catchError(error => this.handleError(error))) as Observable<
      Order[]
    >;
  }

  private handleError(error: any) {
    console.error(error);
    return throwError('Server error (' + error.status + '): ' + error.text());
  }
}
