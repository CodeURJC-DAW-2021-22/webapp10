import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './../../services/login.service';
import { Order } from './../../../models/order.model';
import { OrdersService } from './../../services/order.service';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html'
})
export class OrderListComponent implements OnInit {

  orders: Order[] = [];
  constructor(private router: Router, private service: OrdersService, public loginService: LoginService) { }

  ngOnInit():void {
    this.orders = [{
        price: 12,
        course: 12,
        courseTitle: 'JAVA',
        user: 12,
        userName: 'PEPE',
        paymentMethod: 'string',
        billingAddress: 'string',
        country: 'españa',
        region: 'string',
        dataCard: 'string',
        date: ''
    }]
  }

  newOrder() {
    this.router.navigate(['/orders/new']);
  }
}
