import { Component, OnInit } from '@angular/core';
import { Course } from 'src/models/course.model';
import { CourseService } from 'src/services/course.service';

@Component({
  selector: 'app-courses-list',
  templateUrl: 'courses-list.component.html',
})
export class CoursesListComponent implements OnInit {
  courses: Course[] = [];

  ngOnInit() {
    this.loadCourses();
    console.log(this.courses);
  }

  constructor(private courseService: CourseService) {}

  loadCourses() {
    return this.courseService.getCourses().subscribe((data: Course[]) => {
      this.courses = data;
    });
  }
}
