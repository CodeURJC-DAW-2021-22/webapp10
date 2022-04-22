import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CoursesListComponent } from './pages/CoursesPage/courses-list.component';

const routes: Routes = [
  { path: '', component: AppComponent },
  { path: 'courses', component: CoursesListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
