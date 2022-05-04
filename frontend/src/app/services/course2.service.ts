import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { catchError, map, Observable, pipe, throwError } from 'rxjs';
import { Course } from '../models/course.model';

const base = '/api/';

@Injectable({ providedIn: 'root' })
export class Course2Service {

  constructor(private http: HttpClient) {}

  getCourses(): Observable<Course[]> {
    return this.http
      .get<Course[]>('/api/courses/')
      .pipe(catchError(error => this.handleError(error))) as Observable<Course[]>;
  }

  getCourse(id: number): Observable<Course> {
    return this.http.get<Course>(`api/courses/${id}`);
  }

  private handleError(error: any) {
    console.error(error);
    return throwError('Server error (' + error.status + '): ' + error.text());
  }
}
