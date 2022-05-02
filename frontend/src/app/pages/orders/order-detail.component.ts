import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { OrdersService } from '../../services/order.service';
import { Order } from '../../models/order.model';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
})
export class OrderDetailComponent {
  constructor(
    private router: Router,
    activatedRoute: ActivatedRoute,
    public orderService: OrdersService,
    public loginService: LoginService
  ) {}
  order = {
    price: 12,
    course: 12,
    courseTitle: 'JAVA',
    user: 12,
    userName: 'PEPE',
    paymentMethod: 'TARJETA',
    billingAddress: 'BA',
    country: 'ESPAÑA',
    region: 'MADRID',
    dataCard: '',
    date: '',
  };

  removeOrder() {}
  editOrder() {}
  gotoOrders() {}
}
