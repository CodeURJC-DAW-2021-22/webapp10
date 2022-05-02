import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { OrdersService } from '../../services/order.service';
import { Order } from '../../../models/order.model';
import { LoginService } from '../../services/login.service';
import { User } from '../../../models/user.model';

@Component({
  selector: 'app-myaccount',
  templateUrl: './myaccount.component.html',
})
export class MyaccountComponent implements OnInit {
  user: User = {
    id: 2,
    email: 'admin@mail.com',
    firstName: 'admin@mail.com',
    lastName: 'Ramirez',
    roles: ['USER', 'ADMIN'],
  };
  orders: Order[] = [];

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private orderService: OrdersService,
    public loginService: LoginService
  ) {}

  ngOnInit() {
    this.orderService.getOrders().subscribe((data: Order[]) => {
      this.orders = data;
    });
  }
}
