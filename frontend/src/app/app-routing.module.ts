import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { OrderListComponent } from './pages/orders/order-list.component';
import { OrderDetailComponent } from './pages/orders/order-detail.component';
import { OrderFormComponent } from './pages/orders/order-form.component';
import { CoursesListComponent } from './pages/CoursesPage/courses-list.component';


const routes: Routes = [
  { path: '', component: AppComponent },
  { path: 'courses', component: CoursesListComponent },
  { path: 'orders', component: OrderListComponent },
  { path: 'orders/:id', component: OrderDetailComponent },
  { path: 'orders/checkout', component: OrderFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
