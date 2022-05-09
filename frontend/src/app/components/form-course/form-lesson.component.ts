import { Component, Output, EventEmitter } from '@angular/core';
import { Lesson } from 'src/app/models/lesson.model';
import { User } from 'src/app/models/user.model';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-form-lesson',
  templateUrl: './form-lesson.component.html',
})
export class FormLessonComponent {
  title = '';
  description = '';
  videoUrl = '';
  thumbnail!: File;
  imageId = 0;
  author!: User;
  @Output() lessonEvent = new EventEmitter<Lesson>();

  constructor(private courseService: CourseService) {}

  updateThumbnail(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;

    if (files.length > 0) {
      this.thumbnail = files[0];
    }
  }

  createLesson() {
    this.courseService
      .uploadLessonThumbnail(this.thumbnail)
      .subscribe((data: number) => {
        this.imageId = data;

        const newLesson = {
          title: this.title,
          description: this.description,
          videoUrl: this.videoUrl,
          imageId: this.imageId,
          author: this.author,
        };

        this.lessonEvent.emit(newLesson);
      });
  }
}
