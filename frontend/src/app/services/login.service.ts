import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User, UserRole } from '../models/user.model';
import { catchError, Observable, throwError } from 'rxjs';

const base = '/api/';

@Injectable({ providedIn: 'root' })
export class LoginService {
  logged: boolean = false;
  user: User;
  response: boolean = false;

  constructor(private http: HttpClient) {
    this.reqIsLogged();
    this.user = {
      email: '',
      firstName: '',
      lastName: '',
      encodedPassword: '',
      roles: [],
    };
  }

  logIn(user: string, pass: string) {
    return this.http
      .post<User>(
        '/api/auth/login',
        { username: user, password: pass },
        { withCredentials: true }
      )
      .pipe(
        response => this.catchUser(response),
        catchError(error => this.handleError(error))
      ) as Observable<User>;
  }

  logOut() {
    return this.http
      .post('/api/auth/logout', { withCredentials: true })
      .subscribe((resp: any) => {
        localStorage.setItem('logged', 'false');
        this.logged = false;
        this.user = {
          email: '',
          firstName: '',
          lastName: '',
          encodedPassword: '',
          roles: [],
        };
      });
  }

  register(user: User) {
    return this.http
      .post<User>('/api/users/', user, { withCredentials: true })
      .pipe(catchError(error => this.handleError(error))) as Observable<User>;
  }

  isLogged() {
    return this.logged;
  }

  private checkRole(role: UserRole) {
    return new Promise((res: (isAdm: boolean) => void) => {
      this.currentUser().subscribe(({ roles }: User) => {
        res(roles.indexOf(role) !== -1);
      });
    });
  }

  isAdmin() {
    return this.checkRole(UserRole.ADMIN);
  }

  isTeacher() {
    return this.checkRole(UserRole.TEACHER);
  }

  currentUser(): Observable<User> {
    return this.http.get<User>(`/api/users/me`);
  }

  getUsers(): Observable<User[]> {
    return this.http.get('/api/users/').pipe() as Observable<User[]>;
  }

  reqIsLogged(): Observable<User> {
    return this.http
      .get<User>('/api/users/me', { withCredentials: true })
      .pipe(catchError(error => this.handleError(error))) as Observable<User>;
  }

  private handleError(error: any) {
    alert('Incorrect Credentials');
    console.error(error);
    return throwError('Server error (' + error.status + '): ' + error.text());
  }

  private catchUser(any: any) {
    this.logged = true;
    localStorage.setItem('logged', 'true');
    this.user = any;
    return any;
  }
}
