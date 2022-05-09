import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../../models/order.model';
import { User, UserRole } from '../../models/user.model';
import { Course } from '../../models/course.model';
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
  isAdmin: boolean = false;

  constructor(
    private router: Router,
    private courseService: CourseService,
    private orderService: OrdersService,
    private loginService: LoginService
  ) {
    this.loginService.isAdmin().then((isAdmin: boolean) => {
      this.isAdmin = isAdmin;
    });
  }

  ngOnInit() {
    this.courseService.getAllCourses().subscribe((data: Course[]) => {
      this.courses = data;
      console.log(this.courses);
    });

    this.orderService.getOrders().subscribe((data: Order[]) => {
      this.orders = data;
    });

    this.loginService.getUsers().subscribe((data: User[]) => {
      this.users = data;
    });
  }

  deleteCourse(id: number | undefined) {
    this.courseService.deleteCourse(id!).subscribe(() => {
      this.router.navigate(['/new']);
    });
  }
}
