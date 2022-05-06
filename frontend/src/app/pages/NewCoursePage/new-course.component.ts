import { Byte } from '@angular/compiler/src/util';
import { Component } from '@angular/core';
import { Lesson } from 'src/app/models/lesson.model';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-new-course',
  templateUrl: './new-course.component.html',
  styleUrls: ['./new-course.component.css'],
})
export class NewCourseComponent {
  title = '';
  description = '';
  price = 0;
  thumbnail: Byte[] = [];
  tags: string[] = [];
  isFree = false;
  termsAgreed = false;
  lessons: Lesson[] = [];
  isAddingLesson = false;

  constructor(private courseService: CourseService) {}

  createCourse() {
    console.log('Created!');
  }

  showNewLesson() {
    this.isAddingLesson = true;
  }
}
