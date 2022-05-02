import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from 'src/models/course.model';
import { Page } from 'src/models/page.model';

@Injectable({ providedIn: 'root' })
export class CourseService {
  constructor(private httpClient: HttpClient) {}

  getCourses(searchTerm: string, page: number): Observable<Page<Course>> {
    return this.httpClient.get<Page<Course>>(
      `api/courses/page?search=${searchTerm}&page=${page}`
    );
  }
}
