import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from "src/app/services/login.service";;

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
  
  ngOnInit(): void {
    this.logged = this.loginService.isLogged() || (localStorage.getItem('logged') ==  'true');
    console.log(this.logged);
  }

  constructor(public loginService: LoginService, public router: Router) {
    this.logged = this.loginService.isLogged() || (localStorage.getItem('logged') ==  'true');
    router.events.subscribe((val) => {
      this.logged = this.loginService.isLogged() || (localStorage.getItem('logged') ==  'true'); 
    });
   }

  logOut() {
    this.loginService.logged = false;
    this.logged = false;
    this.loginService.logOut();
  }

}
