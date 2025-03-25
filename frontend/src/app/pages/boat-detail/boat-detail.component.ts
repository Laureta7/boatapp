import { Component, OnInit } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';
import { Boat } from '@interfaces/boat';
import {
  UbCardDescriptionDirective,
  UbCardDirective,
  UbCardContentDirective,
  UbCardFooterDirective,
  UbCardHeaderDirective,
  UbCardTitleDirective,
} from '@components/ui/card';
import { UbButtonDirective } from '@components/ui/button';
import { CommonModule } from '@angular/common';
import { BoatFormService } from '@services/boat-form.service';

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
    ReactiveFormsModule,
  ],
  providers: [BoatFormService],

  templateUrl: './boat-detail.component.html',
})
export class BoatDetailComponent implements OnInit {
  boat!: Boat;
  boatForm: FormGroup;
  isEditMode = false;

  constructor(
    private boatFormService: BoatFormService,
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
  ) {
    this.boatForm = this.boatFormService.createBoatForm();
  }

  ngOnInit(): void {
    const boatId = this.route.snapshot.paramMap.get('id')!;
    this.getBoatDetails(boatId);
  }

  getBoatDetails(id: string): void {
    this.http.get<Boat>(`${environment.apiUrl}/boats/${id}`).subscribe({
      next: (response) => {
        this.boat = response;
        this.boatForm.patchValue(response); //  Fill form
      },
      error: (error) => {
        console.error('Error fetching boat details:', error);
      },
    });
  }

  toggleEditMode(): void {
    if (this.isEditMode) {
      this.resetForm();
    }
    this.isEditMode = !this.isEditMode;
  }

  resetForm(): void {
    this.boatForm.reset();
    this.getBoatDetails(this.boat.id);
  }

  saveBoat(): void {
    if (this.boatForm.valid) {
      const updatedBoat = this.boatForm.value;
      if (updatedBoat.registrationNumber === '') {
        updatedBoat.registrationNumber = null;
      }

      this.http
        .put(`${environment.apiUrl}/boats/${this.boat.id}`, updatedBoat)
        .subscribe({
          next: () => {
            // Update boat details with new values
            this.boat = { ...this.boat, ...updatedBoat };
            this.isEditMode = false;
          },
          error: (error) => {
            console.error('Error updating boat details:', error);
          },
        });
    } else {
      console.error('Form is not valid');
    }
  }

  deleteBoat(): void {
    this.http.delete(`${environment.apiUrl}/boats/${this.boat.id}`).subscribe({
      next: () => {
        this.router.navigate(['/boats']);
      },
      error: (error) => {
        console.error('Error deleting boat:', error);
      },
    });
  }

  goBack(): void {
    this.router.navigate(['/boats']);
  }
}
