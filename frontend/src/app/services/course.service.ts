import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Course } from 'src/app/models/course.model';
import { Page } from 'src/app/models/page.model';

const baseUrl = '/api/courses';

@Injectable({ providedIn: 'root' })
export class CourseService {
  constructor(private httpClient: HttpClient) {}

  getCourses(searchTerm: string, page: number): Observable<Page<Course>> {
    if (searchTerm === '')
      return this.httpClient.get<Page<Course>>(`${baseUrl}/page?page=${page}`);

    return this.httpClient.get<Page<Course>>(
      `${baseUrl}/page?search=${searchTerm}&page=${page}`
    );
  }

  getAllCourses(): Observable<Course[]> {
    return this.httpClient.get<Course[]>(`${baseUrl}/`);
  }

  getCourse(id: number): Observable<Course> {
    return this.httpClient.get<Course>(`${baseUrl}/${id}`);
  }

  uploadLessonThumbnail(thumbnail: File): Observable<number> {
    const formData = new FormData();
    formData.append('image', thumbnail);

    return this.httpClient.post<number>(`/api/videoThumbnail`, formData);
  }

  saveCourse({
    title,
    description,
    price,
    thumbnail,
    tags,
    lessons,
  }: Course): Observable<Course> {
    const formData = new FormData();
    formData.append('title', title);
    formData.append('description', description);
    formData.append('price', price.toString());
    formData.append('image', thumbnail ?? new File([], ''));
    formData.append('tags', JSON.stringify(tags));
    formData.append('lessons', JSON.stringify(lessons));

    return this.httpClient.post<Course>(`${baseUrl}/`, formData);
  }

  editCourse({
    title,
    description,
    price,
    thumbnail,
    tags,
    lessons,
  }: Course): Observable<Course> {
    const formData = new FormData();
    formData.append('title', title);
    formData.append('description', description);
    formData.append('price', price.toString());
    formData.append('image', thumbnail ?? new File([], ''));
    formData.append('tags', JSON.stringify(tags));
    formData.append('lessons', JSON.stringify(lessons));

    return this.httpClient.put<Course>(`${baseUrl}/`, formData);
  }

  deleteCourse(id: number) {
    return this.httpClient
      .delete<Course>(baseUrl + '/delete/' + id, { withCredentials: true })
      .pipe(catchError(error => this.handleError(error))) as Observable<Course>;
  }

  private handleError(error: any) {
    alert('Incorrect Credentials');
    console.error(error);
    return throwError('Server error (' + error.status + '): ' + error.text());
  }
}
