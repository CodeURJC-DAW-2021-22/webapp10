import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Course } from '../../models/course.model';

const BASE_URL = '/api/courses/';

@Injectable({ providedIn: 'root' })
export class CourseService {
  constructor(private httpClient: HttpClient) {}

  getCourses():Observable<Course[]> {
    return this.httpClient.get(BASE_URL).pipe(
      ) as Observable<Course[]>;
  }
}

