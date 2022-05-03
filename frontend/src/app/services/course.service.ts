import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from 'src/app/models/course.model';
import { Page } from 'src/app/models/page.model';

@Injectable({ providedIn: 'root' })
export class CourseService {
  constructor(private httpClient: HttpClient) {}

  getCourses(searchTerm: string, page: number): Observable<Page<Course>> {
    if (searchTerm === '')
      return this.httpClient.get<Page<Course>>(`api/courses/page?page=${page}`);

    return this.httpClient.get<Page<Course>>(
      `api/courses/page?search=${searchTerm}&page=${page}`
    );
  }

  getAllCourses(): Observable<Course[]> {
    return this.httpClient.get<Course[]>(`api/courses`);
  }

  getCourse(id: number): Observable<Course> {
    return this.httpClient.get<Course>(`api/courses/${id}`);
  }
}
