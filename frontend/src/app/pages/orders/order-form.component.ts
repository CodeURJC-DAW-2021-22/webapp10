import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './../../services/login.service';
import { Order } from './../../../models/order.model';
import { Course } from './../../../models/course.model';
import { OrdersService } from './../../services/order.service';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html'
})
export class OrderFormComponent implements OnInit {

  constructor(private router: Router, private service: OrdersService, public loginService: LoginService) { }

  ngOnInit():void {

        
  }

  newOrder() {
    this.router.navigate(['/orders/new']);
  }
}