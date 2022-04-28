import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from "src/app/services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = "";
  password = "";

  ngOnInit(): void {
  }

  constructor(public loginService: LoginService, public router: Router) { }

  logIn() {
    this.loginService.logIn(this.email, this.password);
    if (this.loginService.isLogged()) {
      this.router.navigate(['/courses']);
    }
  }

  logOut() {
    this.loginService.logOut();
  }
  

}
