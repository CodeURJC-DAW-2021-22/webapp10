import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../../../models/order.model';
import { User } from '../../../models/user.model';
import { Course } from '../../../models/course.model';
import { OrdersService } from '../../services/order.service';
import { LoginService } from '../../services/login.service';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
})
export class AdminComponent implements OnInit {
  orders: Order[] = [];
  users: User[] = [];
  courses: Course[] = [];
  isAdmin: boolean;

  constructor(
    private router: Router,
    private courseService: CourseService,
    private orderService: OrdersService,
    private loginService: LoginService
  ) {
    this.isAdmin = this.loginService.isAdmin() || true;
  }

  ngOnInit() {
    this.courseService.getCourses().subscribe((data: Course[]) => {
      this.courses = data;
    });

    this.orderService.getOrders().subscribe((data: Order[]) => {
      this.orders = data;
    });

    this.loginService.getUsers().subscribe((data: User[]) => {
      this.users = [
        {
          id: 2,
          email: 'admin@mail.com',
          firstName: 'admin@mail.com',
          lastName: 'Ramirez',
          roles: ['USER', 'ADMIN'],
        },
      ];
    });
  }

  goHome() {
    this.router.navigate(['/']);
  }
}
