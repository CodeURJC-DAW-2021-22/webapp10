import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { CoursesComponent } from './components/courses/courses.component';
import { OrderListComponent } from './components/orders/order-list.component';
import { OrderDetailComponent } from './components/orders/order-detail.component';
import { OrderFormComponent } from './components/orders/order-form.component';
import { AdminComponent } from './components/admin/admin.component';
import { MyaccountComponent } from './components/myaccount/myaccount.component';

const appRoutes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'home', component: HomeComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'orders', component: OrderListComponent },
  { path: 'orders/detail/:id', component: OrderDetailComponent },
  { path: 'orders/checkout', component: OrderFormComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'myaccount/:id', component: MyaccountComponent },
];
export const routing = RouterModule.forRoot(appRoutes);
