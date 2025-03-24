import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';
import { Boat } from '@interfaces/boat';
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
import { UbInputDirective } from '@components/ui/input';

@Component({
  selector: 'app-boat-detail',
  imports: [
    CommonModule,
    UbCardDirective,
    UbCardHeaderDirective,
    UbCardTitleDirective,
    UbCardDescriptionDirective,
    UbCardContentDirective,
    UbCardFooterDirective,
    UbButtonDirective,
    UbButtonDirective,
  ],
  templateUrl: './boat-detail.component.html',
  styleUrls: ['./boat-detail.component.css'],
})
export class BoatDetailComponent implements OnInit {
  boatId: string = '';
  boat!: Boat;
  private apiUrl = environment.apiUrl;

  private router = inject(Router);
  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
  ) {}

  ngOnInit(): void {
    this.boatId = this.route.snapshot.paramMap.get('id') || '';
    this.getBoatDetails(this.boatId);
  }

  getBoatDetails(id: string): void {
    this.http.get<Boat>(`${this.apiUrl}/boats/${id}`).subscribe({
      next: (response) => {
        this.boat = response;
      },
      error: (error) => {
        console.error('Error fetching boat details:', error);
      },
    });
  }

  goBack(): void {
    this.router.navigate(['/boats']); // Redirection vers la liste des bateaux
  }

  editBoat(): void {
    // this.router.navigate(['/boats/edit', this.boat.id]); // Redirection vers l'édition
  }
}
