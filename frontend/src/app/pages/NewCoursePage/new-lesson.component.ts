import { Component, Output, EventEmitter } from '@angular/core';
import { Lesson } from 'src/app/models/lesson.model';
import { User } from 'src/app/models/user.model';
import { CourseService } from 'src/app/services/course.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-new-lesson',
  templateUrl: './new-lesson.component.html',
})
export class NewLessonComponent {
  title = '';
  description = '';
  videoUrl = '';
  thumbnail!: File;
  imageId = 0;
  author!: User;
  @Output() lessonEvent = new EventEmitter<Lesson>();

  constructor(
    private loginService: LoginService,
    private courseService: CourseService
  ) {
    this.loginService
      .currentUser()
      .subscribe((currentUser: User) => (this.author = currentUser));
  }

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

        console.log(newLesson);

        this.lessonEvent.emit(newLesson);
      });
  }
}