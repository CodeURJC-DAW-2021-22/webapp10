import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { User } from 'src/app/models/user.model';

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
      this.router.navigate(['/new/courses']);
    }
  }

  register() {
    this.loginService.register(this.user).subscribe(user => {
      localStorage.setItem('logged', 'false');
      alert('Register: Successfully');
      this.router.navigate(['/login']);
    });
  }
}
