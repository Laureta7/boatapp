import { Component, inject, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import {
  UbCardDescriptionDirective,
  UbCardDirective,
  UbCardContentDirective,
  UbCardFooterDirective,
  UbCardHeaderDirective,
  UbCardTitleDirective,
} from '@components/ui/card';
import { UbButtonDirective } from '@components/ui/button';
import { Boat } from '@interfaces/boat';

@Component({
  selector: 'app-boats',
  imports: [
    CommonModule,
    UbCardDirective,
    UbCardHeaderDirective,
    UbCardTitleDirective,
    UbCardDescriptionDirective,
    UbCardContentDirective,
    UbCardFooterDirective,
    UbButtonDirective,
  ],

  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.css'],
})
export class BoatsComponent implements OnInit {
  title = 'boats';
  boats: Boat[] = [];

  private apiUrl = environment.apiUrl;
  private router = inject(Router);

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getBoats();
  }

  getBoats(): void {
    this.http.get<Boat[]>(`${this.apiUrl}/boats`).subscribe({
      next: (response) => {
        this.boats = response;
        console.log(this.boats);
      },
      error: (error) => {
        console.error('Error fetching boats:', error);
      },
    });
  }

  goToBoat(id: string): void {
    console.log('Navigating to boat:', id);
    this.router.navigate(['/boats', id]);
  }
}
