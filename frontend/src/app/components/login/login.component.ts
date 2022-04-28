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
    if (this.loginService.isLogged()) {
      this.router.navigate(['/courses']);
    }
  }

  constructor(public loginService: LoginService, public router: Router) { }

  logIn() {
    await this.loginService.logIn(this.email, this.password);
    console.log(this.loginService.isLogged());
    if (this.loginService.isLogged() || (localStorage.getItem('logged') ==  'true')) {
      this.router.navigate(['/courses']);
    }
  }

  logOut() {
    this.loginService.logOut();
  }
  

}
