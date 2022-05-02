import { Component, OnInit } from '@angular/core';
import { Course } from 'src/models/course.model';
import { Page } from 'src/models/page.model';
import { CourseService } from 'src/services/course.service';

@Component({
  selector: 'app-courses-list',
  templateUrl: 'courses-list.component.html',
})
export class CoursesListComponent implements OnInit {
  courses: Course[] = [];
  searchTerm = 'Gaming';
  currentPage = 0;
  lastPage = false;

  ngOnInit() {
    this.loadCourses();
    console.log(this.courses);
  }

  constructor(private courseService: CourseService) {}

  setCourses = ({ content, last }: Page<Course>) => {
    this.courses = [...this.courses, ...content];
    this.lastPage = last;
  };

  loadCourses() {
    this.courseService
      .getCourses(this.searchTerm, this.currentPage)
      .subscribe(this.setCourses);

    this.currentPage++;
  }
}
