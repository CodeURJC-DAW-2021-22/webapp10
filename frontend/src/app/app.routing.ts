import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { CoursesComponent } from './components/courses/courses.component';



const appRoutes = [
{ path: '', redirectTo: 'home', pathMatch: 'full' },
{ path: 'login', component: LoginComponent},
{ path: 'signup', component: SignupComponent},
{ path: 'home', component: HomeComponent},
{ path: 'courses', component: CoursesComponent},
]
export const routing = RouterModule.forRoot(appRoutes);