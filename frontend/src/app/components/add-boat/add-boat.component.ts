import { Component, EventEmitter, Output } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';
import { Boat } from '@interfaces/boat';
import {
  UbCardDescriptionDirective,
  UbCardDirective,
  UbCardHeaderDirective,
  UbCardTitleDirective,
} from '@components/ui/card';
import { UbButtonDirective } from '@components/ui/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-boat',
  imports: [
    CommonModule,
    UbCardDirective,
    UbCardHeaderDirective,
    UbCardTitleDirective,
    UbCardDescriptionDirective,
    UbButtonDirective,
    ReactiveFormsModule,
  ],
  templateUrl: './add-boat.component.html',
  styleUrls: ['./add-boat.component.css'],
})
export class AddBoatComponent {
  boatForm: FormGroup;
  @Output() boatAdded = new EventEmitter<Boat>();
  @Output() canceled = new EventEmitter<void>();
  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
  ) {
    this.boatForm = this.fb.group({
      name: ['', Validators.required],
      ownerName: ['', Validators.required],
      description: ['', Validators.required],
      year: [
        '',
        [
          Validators.required,
          Validators.min(1900),
          Validators.max(new Date().getFullYear()),
        ],
      ],
      length: ['', [Validators.required, Validators.min(1)]],
      price: ['', [Validators.required, Validators.min(0)]],
      registrationNumber: ['', Validators.required],
    });
  }

  addBoat(): void {
    if (this.boatForm.valid) {
      const newBoat: Boat = this.boatForm.value;
      this.http.post<Boat>(`${environment.apiUrl}/boats`, newBoat).subscribe({
        next: (response: Boat) => {
          this.boatAdded.emit(response); // Emit event with new boat
          this.boatForm.reset(); // Reset form
        },
        error: (error) => {
          console.error('Error adding boat:', error);
        },
      });
    } else {
      console.error('Form is not valid');
    }
  }

  goBack(): void {
    //emit event
    this.boatForm.reset();
    this.canceled.emit();
  }
}
