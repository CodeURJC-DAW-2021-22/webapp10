import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/models/course.model';
import { Lesson } from 'src/app/models/lesson.model';
import { User } from 'src/app/models/user.model';
import { CourseService } from 'src/app/services/course.service';
import { LoginService } from 'src/app/services/login.service';
import { OrdersService } from 'src/app/services/order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-page',
  templateUrl: 'course-page.component.html',
})
export class CoursePageComponent implements OnInit {
  course: Course = {
    title: '',
    description: '',
    thumbnail: new File([], ''),
    price: 0,
    tags: [],
    lessons: [],
    author: {
      email: '',
      firstName: '',
      lastName: '',
      roles: [],
    },
  };

  logged: boolean = false;
  isAdmin: boolean = false;
  isOwner: boolean = false;
  currentUser!: User;
  currentLesson: Lesson | null = null;

  ngOnInit() {
    const courseId = parseInt(
      this.activatedRoute.snapshot.paramMap.get('id') ?? '0'
    );

    this.courseService.getCourse(courseId).subscribe(course => {
      console.log(course);
      this.course = course;
    });

    this.logged =
      this.loginService.logged || localStorage.getItem('logged') == 'true';

    if (this.logged || localStorage.getItem('logged') == 'true') {
      this.loginService.isAdmin().then(admin => (this.isAdmin = admin));

      this.loginService.currentUser().subscribe(user => {
        this.currentUser = user;
        this.isOwner = user.id === this.course.author.id;
      });
    }
  }

  constructor(
    private courseService: CourseService,
    private loginService: LoginService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  showVideo(id: number) {
    this.currentLesson = this.course.lessons[id];
  }

  deleteCourse() {
    this.courseService.deleteCourse(this.course.id!).subscribe(() => {
      this.router.navigate(['/new/courses']);
    });
  }

  buy() {
    this.router.navigate(['/new/orders/checkout/' + this.course.id]);
  }
}
