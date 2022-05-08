import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-new-course',
  templateUrl: 'new-course.component.html',
})
export class NewCourseComponent {
  constructor(private loginService: LoginService, private router: Router) {
    this.loginService.isTeacher().then(isTeacher => {
      this.loginService.isAdmin().then(isAdmin => {
        if (!isAdmin && !isTeacher) this.router.navigate(['/new/courses']);
      });
    });
  }
}
