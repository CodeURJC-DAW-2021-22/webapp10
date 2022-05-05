import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from './../../services/login.service';
import { Order } from '../../models/order.model';
import { User } from '../../models/user.model';
import { Course } from '../../models/course.model';
import { CourseService } from './../../services/course.service';
import { OrdersService } from './../../services/order.service';

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
})
export class OrderFormComponent implements OnInit {
  order!: Order;
  ccv: string = '';
  expiration: string = '';
  ccnumber: string = '';
  isLogged: boolean = false;
  admin: boolean = false;
  courseId: number = 0;

  constructor(
    private router: Router,
    activatedRoute: ActivatedRoute,
    private service: OrdersService,
    public loginService: LoginService,
    public courseService: CourseService,
    public orderService: OrdersService
  ) {
    const courseId = activatedRoute.snapshot.params['id'];
    this.order = {
      billingAddress: '',
      country: '',
      region: '',
      course: 0,
      courseTitle: '',
      price: 0,
      user: 0,
      userName: '',
      paymentMethod: '',
      dataCard: '',
      date: '',
    };

  }

  ngOnInit(): void {
    this.isLogged =
      this.loginService.isLogged() || localStorage.getItem('logged') == 'true';
    
    //Obtenemos los valores del user que necesitamos para el order
    this.loginService.currentUser().subscribe(({ firstName, id }: User) => {
      this.order.userName = firstName;
      this.order.user = id ?? 0;
    });
    //se meten los valores del curso necesarios al order
    this.courseService.getCourse(this.courseId).subscribe(({ title, price }: Course) => {
      this.order.courseTitle = title;
      this.order.course = this.courseId ?? 0;
      this.order.price = price;
    });

    this.loginService.isAdmin().then((isAdmin: boolean) => {
      this.admin = isAdmin;
    });
  }
  
  setOrder() {
    //se hace la llamada a addOrder
    this.orderService.addOrder(this.order).subscribe(
        response => this.order = response as any,
        error => console.error(error)
    );
    this.router.navigate(['/new/success/', this.order.id]);
  }
}
