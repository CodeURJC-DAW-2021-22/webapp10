import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course.model';
import { Lesson } from 'src/app/models/lesson.model';
import { User } from 'src/app/models/user.model';
import { CourseService } from 'src/app/services/course.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-new-course',
  templateUrl: './new-course.component.html',
  styleUrls: ['./new-course.component.css'],
})
export class NewCourseComponent {
  title = '';
  description = '';
  price = 0;
  thumbnail!: File;
  tags: string[] = [];
  isFree = false;
  termsAgreed = false;
  lessons: Lesson[] = [];
  isAddingLesson = false;
  author!: User;

  constructor(
    private courseService: CourseService,
    private loginService: LoginService,
    private router: Router
  ) {
    this.loginService
      .currentUser()
      .subscribe((currentUser: User) => (this.author = currentUser));
  }

  createCourse() {
    const newCourse = {
      title: this.title,
      description: this.description,
      price: this.isFree ? 0 : this.price,
      thumbnail: this.thumbnail,
      tags: this.tags,
      lessons: this.lessons,
      author: this.author,
    };

    this.courseService.saveCourse(newCourse).subscribe(({ id }: Course) => {
      if (typeof id !== 'undefined') this.router.navigate(['new/courses/', id]);
    });
  }

  updateThumbnail(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;

    if (files.length > 0) {
      this.thumbnail = files[0];
    }
  }

  showNewLesson() {
    this.isAddingLesson = true;
  }

  addLesson(lesson: Lesson) {
    this.isAddingLesson = false;

    this.lessons.push(lesson);
  }
}
