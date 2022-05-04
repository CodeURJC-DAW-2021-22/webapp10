import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from 'src/app/models/course.model';
import { Course2Service } from 'src/app/services/course2.service';

@Component({
  selector: 'app-courses2',
  templateUrl: './courses2.component.html',
  styleUrls: ['./courses2.component.css'],
})
export class Courses2Component implements OnInit {
  
  $courses: Observable<Course[]>;
  lastPage = false;

  constructor(private service: Course2Service) {
    this.$courses = this.service.getCourses();
  }

  ngOnInit(): void {
    alert('ng on init launched');
    this.refresh();
  }

  refresh() {
    this.$courses = this.service.getCourses();
    console.log(this.$courses);
  }

  loadCourses() {}
}
