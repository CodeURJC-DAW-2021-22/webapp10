import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Order } from '../models/order.model';
import { Course } from '../models/course.model';

const BASE_URL = '/api/orders/';

@Injectable({ providedIn: 'root' })
export class OrdersService {

  order: Order;

  constructor(private httpClient: HttpClient) {

    this.order = {
      price: 100,
      user: 0,
      course:  0,
      courseTitle: "mock",
      userName: "mock name",
      paymentMethod: 'Credit Card',
      billingAddress: 'Fake St. 123',
      country: 'Spain',
      region: 'Valencia',
      dataCard: 'VISA',
      date: new Date().toLocaleString(),
    };

    
  }

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

  newOrder(order: Order){
    return this.httpClient
      .post<Order>(
        '/api/orders',
        { withCredentials: true }
      )
      .pipe(
        catchError(error => this.handleError(error))
      ) as Observable<Order>;
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
