import { Component, OnInit } from '@angular/core';
import { Order } from '../../models/order.model';
import { OrdersService } from './../../services/order.service';
import { LoginService } from './../../services/login.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
})
export class OrderListComponent implements OnInit {
  orders: Order[] = [];

  constructor(
    private orderService: OrdersService,
    private loginService: LoginService
  ) {}

  ngOnInit() {
    this.orderService.getOrders().subscribe((data: Order[]) => {
      this.orders = data;
    });
  }
}
