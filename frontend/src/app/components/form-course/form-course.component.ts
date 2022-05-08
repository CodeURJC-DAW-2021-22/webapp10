import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course.model';
import { Lesson } from 'src/app/models/lesson.model';
import { User } from 'src/app/models/user.model';
import { CourseService } from 'src/app/services/course.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-form-course',
  templateUrl: './form-course.component.html',
  styleUrls: ['./form-course.component.css'],
})
export class FormCourseComponent implements OnInit {
  @Input() editMode!: boolean;
  @Input() editCourseId!: number;

  id = 0;
  title = '';
  description = '';
  price = 0;
  thumbnail!: File;
  tags: string[] = [];
  isFree = false;
  termsAgreed = false;
  lessons: Lesson[] = [];
  isAddingLesson = false;
  author!: User;

  ngOnInit() {
    if (this.editMode) {
      if (typeof this.editCourseId === 'undefined')
        throw new Error('Mostrar pagina de error aqui');

      this.courseService
        .getCourse(this.editCourseId)
        .subscribe(
          ({
            id,
            title,
            description,
            price,
            thumbnail,
            tags,
            lessons,
            author,
          }) => {
            this.id = id ?? 0;
            this.title = title;
            this.description = description;
            this.price = price;
            this.thumbnail = thumbnail ?? new File([], '');
            this.tags = tags;
            this.lessons = lessons;
            this.author = author;
          }
        );
    }
  }

  constructor(
    private courseService: CourseService,
    private loginService: LoginService,
    private router: Router
  ) {
    this.loginService
      .currentUser()
      .subscribe((currentUser: User) => (this.author = currentUser));
  }

  handleFormSubmit() {
    if (this.editMode) return this.editCourse();

    return this.createCourse();
  }

  editCourse() {
    const editCourse = {
      id: this.id,
      title: this.title,
      description: this.description,
      price: this.isFree ? 0 : this.price,
      thumbnail: this.thumbnail,
      tags: this.tags,
      lessons: this.lessons,
      author: this.author,
    };

    this.courseService.editCourse(editCourse).subscribe(({ id }: Course) => {
      if (typeof id !== 'undefined') this.router.navigate(['new/courses/', id]);
    });
  }

  createCourse() {
    const newCourse = {
      title: this.title,
      description: this.description,
      price: this.isFree ? 0 : this.price,
      thumbnail: this.thumbnail,
      tags: this.tags,
      lessons: this.lessons,
      author: this.author,
    };

    this.courseService.saveCourse(newCourse).subscribe(({ id }: Course) => {
      if (typeof id !== 'undefined') this.router.navigate(['new/courses/', id]);
    });
  }

  updateThumbnail(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;

    if (files.length > 0) {
      this.thumbnail = files[0];
    }
  }

  showNewLesson() {
    this.isAddingLesson = true;
  }

  addLesson(lesson: Lesson) {
    this.isAddingLesson = false;

    this.lessons.push(lesson);
  }
}
