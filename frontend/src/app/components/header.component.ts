import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent {
  logged = true;
  userName = 'username';
  userId = 0;
  admin = true;
  token = '';
  searchTerm = '';

  constructor(private router: Router) {}

  search() {
    this.router.navigateByUrl(`/courses?searchTerm=${this.searchTerm}`);
  }
}
