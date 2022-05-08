import { Component, Input } from '@angular/core';
import { Lesson } from 'src/app/models/lesson.model';

@Component({
  selector: 'app-form-course-lesson',
  templateUrl: './form-course-lesson.component.html',
})
export class FormCourseLessonComponent {
  @Input() lesson!: Lesson;
}
