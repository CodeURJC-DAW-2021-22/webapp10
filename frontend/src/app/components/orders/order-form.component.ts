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
    private activatedRoute: ActivatedRoute,
    public loginService: LoginService,
    public courseService: CourseService,
    public orderService: OrdersService
  ) {
    this.order = {
      billingAddress: '',
      country: '',
      region: '',
      course: activatedRoute.snapshot.params['id'],
      courseTitle: '',
      price: 0,
      user: 0,
      userName: '',
      paymentMethod: '',
      dataCard: '',
      date: '',
    };

    this.loginService.currentUser().subscribe(({ firstName, id }: User) => {
      this.order.userName = firstName;
      this.order.user = id ?? 0;
    });
    console.log('AFTER USER',this.order);

    

    console.log('AFTER COURSE',this.order);

    this.loginService.isAdmin().then((isAdmin: boolean) => {
      this.admin = isAdmin;
    });
  }

  ngOnInit(): void {
    this.isLogged =
      this.loginService.isLogged() || localStorage.getItem('logged') == 'true';
      this.courseService.getCourse(this.activatedRoute.snapshot.params['id'])
    .subscribe((course: Course) => {
      this.order.courseTitle = course.title;
      this.order.price = course.price;
    });
    console.log('HOLLLLINIT', this.order);
  }
  
  setOrder() {
    
    //se hace la llamada a addOrder
    this.orderService.addOrder(this.order).subscribe(
        response => this.order = response as any,
        error => console.error(error)
    );
    console.log('2FIN',this.order);
    this.router.navigate(['/new/success/', this.order.id]);

  }
}
