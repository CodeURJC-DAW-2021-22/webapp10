import { Component } from '@angular/core';
import { Course } from 'src/models/course.model';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-courses-list',
  templateUrl: 'courses-list.component.html',
})
export class CoursesListComponent {
  courses: Course[] = [];

  ngOnInit() {

  }

  constructor(private courseService: CourseService) {}

  loadCourses() {
    return this.courseService.getCourses().subscribe((data: Course[]) => {
      this.courses = data;
    });
  }
}
