import { Component, Input } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Lesson } from 'src/app/models/lesson.model';

@Component({
  selector: 'app-lesson',
  templateUrl: 'lesson.component.html',
})
export class LessonComponent {
  @Input() lesson!: Lesson;

  constructor(private sanitizer: DomSanitizer) {}

  santizeVideoUrl() {
    return this.sanitizer.bypassSecurityTrustResourceUrl(this.lesson.videoUrl);
  }
}
