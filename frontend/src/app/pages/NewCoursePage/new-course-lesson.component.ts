import { Component, Input } from '@angular/core';
import { Lesson } from 'src/app/models/lesson.model';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-new-course-lesson',
  templateUrl: './new-course-lesson.component.html',
})
export class NewCourseLessonComponent {
  @Input() lesson!: Lesson;
}
