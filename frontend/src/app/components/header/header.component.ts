import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../../app/services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {
  logged: boolean;
  userName = 'username';
  userId = 0;
  admin = true;
  token = '';
  searchTerm = '';

  ngOnInit(): void {
    this.logged =
      this.loginService.isLogged() || localStorage.getItem('logged') == 'true';
  }

  constructor(private loginService: LoginService, private router: Router) {
    this.logged =
      this.loginService.isLogged() || localStorage.getItem('logged') == 'true';
    router.events.subscribe(_val => {
      this.logged =
        this.loginService.isLogged() ||
        localStorage.getItem('logged') == 'true';
    });
  }

  search() {
    this.router.navigateByUrl(`/courses?searchTerm=${this.searchTerm}`);
  }

  logOut() {
    this.loginService.logged = false;
    this.logged = false;
    localStorage.setItem('logged', 'false');
    this.loginService.logOut();
  }
}
