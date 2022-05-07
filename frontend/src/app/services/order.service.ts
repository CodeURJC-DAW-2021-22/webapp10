import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Order } from '../models/order.model';

const BASE_URL = '/api/orders/';

@Injectable({ providedIn: 'root' })
export class OrdersService {
  constructor(private httpClient: HttpClient) {}

  getOrders(): Observable<Order[]> {
    return this.httpClient.get(BASE_URL).pipe() as Observable<Order[]>;
  }

  getOrder(id: number): Observable<Order> {
    return this.httpClient.get(BASE_URL + id).pipe() as Observable<Order>;
  }

  addOrder(order: Order) {
    if (!order.id) {
      return this.httpClient.post<Order>(BASE_URL, order).pipe();
    } else {
      return this.httpClient.put<Order>(BASE_URL + order.id, order).pipe();
    }
  }

  deleteOrder(order: Order) {
    return this.httpClient
      .delete(BASE_URL + order.id)
      .pipe(catchError(error => this.handleError(error)));
  }

  updateOrder(order: Order) {
    return this.httpClient
      .put(BASE_URL + order.id, order)
      .pipe(catchError(error => this.handleError(error)));
  }

  private handleError(error: any) {
    console.log('ERROR:');
    console.error(error);
    return throwError('Server error (' + error.status + '): ' + error.text());
  }
}
