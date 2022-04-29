import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../../models/user.model';

const base = '/api/';

@Injectable({ providedIn: 'root' })
export class LoginService {

    logged: boolean = false;
    user: User | undefined;

    constructor(private http: HttpClient) {
        this.reqIsLogged();
    }

    reqIsLogged() {
        this.http.get('/api/users/me', { withCredentials: true }).subscribe(
            response => {
                this.user = response as User;
                console.log(this.user.name);
                this.logged = true;
                localStorage.setItem('logged', 'true');
            },
            error => {
                if (error.status != 404) {
                    console.error('Error when asking if logged: ' + JSON.stringify(error));
                }
            }
        );

    }


    logIn(user: string, pass: string) {
        this.http.post("/api/auth/login", { username: user, password: pass }, { withCredentials: true })
            .subscribe(
                (response) => {
                    this.logged = true;
                    localStorage.setItem('logged', 'true');
                    this.reqIsLogged();
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