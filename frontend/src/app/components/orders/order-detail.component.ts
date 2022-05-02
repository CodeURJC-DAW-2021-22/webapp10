import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { OrdersService } from '../../services/order.service';
import { Order } from '../../models/order.model';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
})
export class OrderDetailComponent {
  order!: Order;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private orderService: OrdersService,
    public loginService: LoginService
  ) {
    let id = this.activatedRoute.snapshot.params['id'];
    this.orderService.getOrder(id).subscribe((data: Order) => {
      this.order = data;
    });
  }

  removeOrder() {}
  goToOrders() {
    this.router.navigate(['/orders']);
  }
}
