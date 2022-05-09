import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { User, UserRole } from 'src/app/models/user.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  user: User;
  rolesString: String;

  constructor(public loginService: LoginService, public router: Router) {
    this.user = {
      email: '',
      firstName: '',
      lastName: '',
      encodedPassword: '',
      roles: [],
    };
    this.rolesString = '';
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
    if (this.rolesString === 'STUDENT') this.user.roles.push(UserRole.USER);
    if (this.rolesString === 'TEACHER') this.user.roles.push(UserRole.TEACHER);

    this.loginService.register(this.user).subscribe(() => {
      localStorage.setItem('logged', 'false');
      this.router.navigate(['/login']);
    });
  }
}
