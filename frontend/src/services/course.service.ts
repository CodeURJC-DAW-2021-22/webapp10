import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from 'src/models/course.model';

const urlTemplate = (route: string) => `https://localhost:8080${route}`;

@Injectable({ providedIn: 'root' })
export class CourseService {
  constructor(private httpClient: HttpClient) {}

  getCourses(): Observable<Course[]> {
    return this.httpClient.get<Course[]>(urlTemplate('/api/courses'));
  }
}
