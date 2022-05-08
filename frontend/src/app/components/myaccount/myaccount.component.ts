import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { OrdersService } from '../../services/order.service';
import { Order } from '../../models/order.model';
import { LoginService } from '../../services/login.service';
import { User, UserRole } from '../../models/user.model';
import { CourseBoughtTimes } from 'src/app/models/CourseBoughtTimes.model';
import { CourseboughtService } from '../../services/coursebought.service';
import { Chart } from 'node_modules/chart.js';
import { registerables } from 'chart.js';

@Component({
  selector: 'app-myaccount',
  templateUrl: './myaccount.component.html',
})
export class MyaccountComponent implements OnInit {
  user: User;

  isTeacher: boolean = false;

  orders: Order[] = [];

  labels: String[] = [];
  data: number[] = [];

  coursesBoughtTimes: CourseBoughtTimes[] = [];

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private orderService: OrdersService,
    public loginService: LoginService,
    public courseboughtService: CourseboughtService
  ) {
    this.user = this.loginService.user;
    
    this.loginService.currentUser().subscribe(user => {
      this.user = user;
    });
  }

  ngOnInit() {

    this.loginService.currentUser().subscribe(user => {
      this.user = user;
      this.graph();
    });

    
  }


  graph(){ 
    this.courseboughtService
      .getCourseBoughtTimes(this.user)
      .subscribe(coursesArray => {
        this.coursesBoughtTimes = coursesArray;

        this.coursesBoughtTimes.forEach(element => {
          this.labels.push(element.courseTitle);
          this.data.push(element.boughtTimes);
        });
    
        const myChart = new Chart('myChart', {
          type: 'bar',
          data: {
            labels: this.labels,
            datasets: [
              {
                label: '# of Buyers per courses',
                data: this.data,
                backgroundColor: [
                  'rgba(255, 99, 132, 0.2)',
                  'rgba(54, 162, 235, 0.2)',
                  'rgba(255, 206, 86, 0.2)',
                  'rgba(75, 192, 192, 0.2)',
                  'rgba(153, 102, 255, 0.2)',
                  'rgba(255, 159, 64, 0.2)',
                ],
                borderColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(255, 206, 86, 1)',
                  'rgba(75, 192, 192, 1)',
                  'rgba(153, 102, 255, 1)',
                  'rgba(255, 159, 64, 1)',
                ],
                borderWidth: 1,
              },
            ],
          },
          options: {
            scales: {
              y: {
                beginAtZero: true,
              },
            },
          },
        });
      });


    Chart.register(...registerables);
  }
}
