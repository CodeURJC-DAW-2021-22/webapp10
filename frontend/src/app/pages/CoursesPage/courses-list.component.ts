import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { map } from 'rxjs';
import { Course } from 'src/models/course.model';
import { Page } from 'src/models/page.model';
import { CourseService } from 'src/services/course.service';

@Component({
  selector: 'app-courses-list',
  templateUrl: 'courses-list.component.html',
})
export class CoursesListComponent implements OnInit {
  courses: Course[] = [];
  currentPage = 0;
  lastPage = false;
  searchTerm = '';

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(params => {
      this.searchTerm = params.get('searchTerm') ?? '';
      this.courses = [];
      this.currentPage = 0;
      this.lastPage = false;

      this.loadCourses();
    });
  }

  constructor(
    private courseService: CourseService,
    private activatedRoute: ActivatedRoute
  ) {}

  loadCourses() {
    this.courseService
      .getCourses(this.searchTerm, this.currentPage)
      .subscribe(({ content, last }: Page<Course>) => {
        this.courses.push(...content);
        this.lastPage = last;

        console.log(content);
      });

    this.currentPage++;
  }
}
