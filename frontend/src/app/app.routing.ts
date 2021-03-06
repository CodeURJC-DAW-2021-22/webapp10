import { RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { OrderListComponent } from './components/orders/order-list.component';
import { OrderDetailComponent } from './components/orders/order-detail.component';
import { OrderFormComponent } from './components/orders/order-form.component';
import { OrderSuccessComponent } from './components/orders/order-success.component';
import { AdminComponent } from './components/admin/admin.component';
import { MyaccountComponent } from './components/myaccount/myaccount.component';
import { CoursesListComponent } from './pages/CoursesPage/courses-list.component';
import { CoursePageComponent } from './pages/CoursePage/course-page.component';
import { NewCourseComponent } from './pages/NewCoursePage/new-course.component';
import { EditCourseComponent } from './pages/EditCoursePage/edit-course.component';

const appRoutes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'courses', component: CoursesListComponent },
  { path: 'courses/new', component: NewCourseComponent },
  { path: 'courses/:id', component: CoursePageComponent },
  { path: 'courses/edit/:id', component: EditCourseComponent },
  { path: 'orders', component: OrderListComponent },
  { path: 'orders/detail/:id', component: OrderDetailComponent },
  { path: 'orders/checkout/:id', component: OrderFormComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'success/:id', component: OrderSuccessComponent },
  { path: 'myaccount', component: MyaccountComponent },
];
export const routing = RouterModule.forRoot(appRoutes);
