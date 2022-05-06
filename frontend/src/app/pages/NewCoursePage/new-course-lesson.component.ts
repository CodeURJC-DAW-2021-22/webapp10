import { Byte } from '@angular/compiler/src/util';
import { Component } from '@angular/core';
import { Lesson } from 'src/app/models/lesson.model';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-new-course',
  templateUrl: './new-course-lesson.component.html',
})
export class NewCourseLessonComponent {
  title = '';
  description = '';
  price = 0;
  thumbnail: Byte[] = [];
  tags: string[] = [];
  isFree = false;
  termsAgreed = false;
  lessons: Lesson[] = [];

  constructor(private courseService: CourseService) {}

  createCourse() {
    console.log('Created!');
  }
}
