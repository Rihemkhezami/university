// dashboard.component.ts
import { Component, OnInit } from '@angular/core';
import { StatisticsService } from '../_services/statistics.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  counts: any = {
    users: 0,
    matieres: 0,
    classes: 0,
    departements: 0
  };
  errorMessage: string = '';

  constructor(private statisticsService: StatisticsService) { }

  ngOnInit(): void {
    this.loadCounts();
  }

  loadCounts(): void {
    this.statisticsService.getCounts().subscribe({
      next: data => {
        this.counts = data;
      },
      error: err => {
        this.errorMessage = 'Failed to load counts';
        console.error('Error loading counts', err);
      }
    });
  }
}
