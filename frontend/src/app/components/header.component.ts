import { Component } from '@angular/core';

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
}
