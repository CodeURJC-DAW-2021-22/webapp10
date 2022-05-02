import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from './../../../models/order.model';
import { OrdersService } from './../../services/order.service';
import { LoginService } from './../../services/login.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
})
export class OrderListComponent implements OnInit {
  orders: Order[] = [];

  constructor(
    private router: Router,
    private orderService: OrdersService,
    private loginService: LoginService
  ) {}

  ngOnInit() {
    this.orderService.getOrders().subscribe((data: Order[]) => {
      this.orders = data;
    });
  }

  goHome() {
    this.router.navigate(['/']);
  }
}
