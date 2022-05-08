import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/models/course.model';
import { Lesson } from 'src/app/models/lesson.model';
import { Order } from 'src/app/models/order.model';
import { User } from 'src/app/models/user.model';
import { CourseService } from 'src/app/services/course.service';
import { LoginService } from 'src/app/services/login.service';
import { OrdersService } from 'src/app/services/order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-page',
  templateUrl: 'course-page.component.html',
})
export class CoursePageComponent {
  course!: Course;
  logged: boolean = false;
  isAdmin: boolean = false;
  isOwner: boolean = false;
  currentUser!: User;
  currentLesson: Lesson | null = null;

  constructor(
    private courseService: CourseService,
    private loginService: LoginService,
    private orderService: OrdersService,
    private activatedRoute: ActivatedRoute,
    public router: Router

  ) {
    const courseId = parseInt(
      this.activatedRoute.snapshot.paramMap.get('id') ?? '0'
    );
    this.courseService
      .getCourse(courseId)
      .subscribe(course => (this.course = course));

    this.logged = this.loginService.logged || localStorage.getItem('logged') == 'true';

    if (this.logged || localStorage.getItem('logged') == 'true') {
      this.loginService.isAdmin().then(admin => (this.isAdmin = admin));

      this.loginService.currentUser().subscribe(user => {
        this.currentUser = user;
        this.isOwner = user.id === this.course.author.id;
        if(user.id == this.course.author.id){
          alert("you are the owner");
        }
      });
    }
  }

  showVideo(id: number) {
    this.currentLesson = this.course.lessons[id];
  }

  deleteCourse() {
    alert("pressed");
    this.courseService.deleteCourse(this.course.id!).subscribe(course => {
      this.router.navigate(['/new/courses']);
    });
  }

  buy() {
    this.router.navigate(['/new/orders/checkout/'+this.course.id]);
  }
}
