import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { LoginService } from '../../../app/services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {
  logged!: boolean;
  userName = '';
  userId = 0;
  admin = false;
  token = '';
  searchTerm = '';

  ngOnInit() {
    this.logged =
      this.loginService.isLogged() || localStorage.getItem('logged') == 'true';
    this.router.events.subscribe(_val => {
      this.logged =
        this.loginService.isLogged() ||
        localStorage.getItem('logged') == 'true';
    });

    if (this.logged) {
      this.userName = this.loginService.user.email;
      this.loginService.currentUser().subscribe(({ firstName, id }: User) => {
        this.userName = firstName;
        this.userId = id ?? 0;
      });

      this.loginService.isAdmin().then((isAdmin: boolean) => {
        this.admin = isAdmin;
      });
    }
  }

  constructor(private loginService: LoginService, private router: Router) {}

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
