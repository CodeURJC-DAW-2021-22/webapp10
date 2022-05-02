import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { User } from 'src/models/user.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  user: User;
  email = '';
  password = '';
  name = '';
  lastname = '';
  roles: string[] = [];
  rolesString = '';

  constructor(public loginService: LoginService, public router: Router) {
    this.user = {
      email: '',
      firstName: '',
      lastName: '',
      encodedPassword: '',
      roles: ['USER', 'TEACHER'],
    };
  }

  ngOnInit(): void {
    if (
      this.loginService.isLogged() ||
      localStorage.getItem('logged') == 'true'
    ) {
      this.router.navigate(['/courses']);
    }
  }

  register() {
    this.roles = this.rolesString.split(',');
    this.loginService
      .register(this.email, this.password, this.name, this.lastname, this.roles)
      .subscribe(user => {
        this.router.navigate(['/courses']);
      });
    alert('Clicked');
  }
}
