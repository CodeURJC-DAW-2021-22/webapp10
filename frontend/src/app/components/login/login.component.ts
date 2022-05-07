import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../../app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  email = '';
  password = '';

  ngOnInit(): void {
    if (this.loginService.isLogged()) {
      this.router.navigate(['/new/courses']);
    }
  }

  constructor(public loginService: LoginService, public router: Router) {
    if (this.loginService.isLogged()) {
      this.router.navigate(['/new/courses']);
    }
  }

  logIn() {
    this.loginService.logIn(this.email, this.password).subscribe(user => {
      this.router.navigate(['/new/courses']);
    });
  }

  logOut() {
    this.loginService.logOut();
  }
}
