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
  { path: 'new', component: HomeComponent },
  { path: 'new/login', component: LoginComponent },
  { path: 'new/signup', component: SignupComponent },
  { path: 'new/courses', component: CoursesListComponent },
  { path: 'new/courses/new', component: NewCourseComponent },
  { path: 'new/courses/:id', component: CoursePageComponent },
  { path: 'new/courses/edit/:id', component: EditCourseComponent },
  { path: 'new/orders', component: OrderListComponent },
  { path: 'new/orders/detail/:id', component: OrderDetailComponent },
  { path: 'new/orders/checkout/:id', component: OrderFormComponent },
  { path: 'new/admin', component: AdminComponent },
  { path: 'new/success/:id', component: OrderSuccessComponent },
  { path: 'new/myaccount', component: MyaccountComponent },
];
export const routing = RouterModule.forRoot(appRoutes);
