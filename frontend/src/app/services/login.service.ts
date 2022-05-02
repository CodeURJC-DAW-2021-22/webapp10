import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../../models/user.model';
import { catchError, map, Observable, throwError } from 'rxjs';

const base = '/api/';

@Injectable({ providedIn: 'root' })
export class LoginService {
  logged: boolean = false;
  user: User | undefined;
  response: boolean = false;

  constructor(private http: HttpClient) {
    this.reqIsLogged();
  }

  logIn(user: string, pass: string) {
    return this.http
      .post<User>(
        '/api/auth/login',
        { username: user, password: pass},
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
        console.log('LOGOUT: Successfully');
        this.logged = false;
        this.user = undefined;
      });
  }

  register(user:User) {
    console.log(user);
    return this.http
      .post<User>('/api/users/', user, { withCredentials: true })
      .pipe(catchError(error => this.handleError(error))) as Observable<User>;
  }

  isLogged() {
    return this.logged;
  }

  isAdmin() {
    return this.user && this.user.roles.indexOf('ADMIN') !== -1;
  }

  currentUser() {
    return this.user;
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
    console.error(error);
    return throwError('Server error (' + error.status + '): ' + error.text());
  }

  private catchUser(any: any) {
    this.logged = true;
    localStorage.setItem('logged', 'true');
    return any;
  }
}
