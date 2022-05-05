import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { routing } from './app.routing';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { CoursesListComponent } from './pages/CoursesPage/courses-list.component';
import { CourseCardComponent } from './pages/CoursesPage/course-card.component';
import { OrderListComponent } from './components/orders/order-list.component';
import { OrderFormComponent } from './components/orders/order-form.component';
import { OrderDetailComponent } from './components/orders/order-detail.component';
import { OrderSuccessComponent } from './components/orders/order-success.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { HomeComponent } from './components/home/home.component';
import { LoginService } from './services/login.service';
import { AdminComponent } from './components/admin/admin.component';
import { FormsModule } from '@angular/forms';
import { CoursePageComponent } from './pages/CoursePage/course-page.component';
import { LessonComponent } from './pages/CoursePage/lesson.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    CoursesListComponent,
    CourseCardComponent,
    CoursePageComponent,
    LessonComponent,
    LoginComponent,
    SignupComponent,
    OrderFormComponent,
    HomeComponent,
    OrderDetailComponent,
    OrderListComponent,
    OrderSuccessComponent,
    AdminComponent,
  ],
  imports: [BrowserModule, NgbModule, routing, HttpClientModule, FormsModule],
  providers: [LoginService],

  bootstrap: [AppComponent],
})
export class AppModule {}