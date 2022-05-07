import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { Course } from 'src/app/models/course.model';
import { Page } from 'src/app/models/page.model';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-courses-list',
  templateUrl: 'courses-list.component.html',
})
export class CoursesListComponent implements OnInit {
  courses: Course[] = [];
  currentPage = 0;
  lastPage = false;
  searchTerm = '';
  isTeacher = false;

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(params => {
      this.searchTerm = params.get('searchTerm') ?? '';
      this.courses = [];
      this.currentPage = 0;
      this.lastPage = false;

      this.loadCourses();
    });

    this.loginService
      .isTeacher()
      .then((teacher: boolean) => (this.isTeacher = teacher));
  }

  constructor(
    private courseService: CourseService,
    private activatedRoute: ActivatedRoute,
    private loginService: LoginService
  ) {}

  loadCourses() {
    this.courseService
      .getCourses(this.searchTerm, this.currentPage)
      .subscribe(({ content, last }: Page<Course>) => {
        this.courses.push(...content);
        this.lastPage = last;
      });

    this.currentPage++;
  }
}
