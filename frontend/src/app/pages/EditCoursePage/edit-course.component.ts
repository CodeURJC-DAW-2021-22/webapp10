import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-course',
  templateUrl: 'edit-course.component.html',
})
export class EditCourseComponent {
  courseId: number;

  constructor(private activatedRoute: ActivatedRoute) {
    this.courseId = parseInt(
      this.activatedRoute.snapshot.paramMap.get('id') ?? '0'
    );
  }
}
