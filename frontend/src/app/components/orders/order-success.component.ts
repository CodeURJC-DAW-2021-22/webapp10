import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-order-success',
  templateUrl: './order-success.component.html',
})
export class OrderSuccessComponent {

    orderId:number;

    constructor( private router: Router, activatedRoute: ActivatedRoute) {       
         this.orderId = activatedRoute.snapshot.params['id'];
    }
}