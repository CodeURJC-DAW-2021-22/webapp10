import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})

export class HeaderComponent implements OnInit{
  
  logged: boolean;
  userName = 'username';
  userId = 0;
  admin = true;
  token = '';
  
  constructor(public loginService: LoginService, public router: Router) {
    this.logged = this.loginService.isLogged();
   }
  
  ngOnInit(): void {
    this.logged = this.loginService.isLogged();
    console.log(this.logged);
  }
  
  logOut() {
    this.loginService.logOut();
  }

}
