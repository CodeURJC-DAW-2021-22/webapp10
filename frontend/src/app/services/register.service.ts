import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../../models/user.model';
import { Observable } from 'rxjs';

const base = '/api/';

@Injectable({ providedIn: 'root' })
export class RegisterService {

    logged!: boolean;
    user: User | undefined;
    httpClient: any;

    constructor(private http: HttpClient) {
        this.reqIsLogged();
    }

    reqIsLogged() {
        this.http.get('/api/users/me', { withCredentials: true }).subscribe(
            response => {
                this.user = response as User;
                console.log(this.user.name);
                this.logged = true;
            },
            error => {
                if (error.status != 404) {
                    console.error('Error when asking if logged: ' + JSON.stringify(error));
                }
            }
        );

    }

    getUsers():Observable<User[]> {
		return this.httpClient.get('/api/users/').pipe(
			) as Observable<User[]>;
	}

   /* getUser(id:number):Observable<User> {
		return this.httpClient.get('/api/user/'+ id).pipe(
			) as Observable<User>;
	}*/

    getUser(id:number){};

    logIn(user: string, pass: string) {
        this.http.post("/api/auth/login", { username: user, password: pass }, { withCredentials: true })
            .subscribe(
                (response) => {
                    this.reqIsLogged()
                    localStorage.setItem('logged', 'true');
                },
                (error) => alert("Wrong credentials " + user +" "+ pass)
            );
    }

    logOut() {
        return this.http.post('/api/auth/logout', { withCredentials: true })
            .subscribe((resp: any) => {
                localStorage.setItem('logged', 'false');
                console.log("LOGOUT: Successfully");
                this.logged = false;
                this.user = undefined;
            });

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
}