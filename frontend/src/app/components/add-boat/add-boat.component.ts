import { Component, EventEmitter, Output } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
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
import { BoatRequest } from '@app/interfaces/boat-request';
import { BoatFormService } from '@services/boat-form.service';

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
    private boatFormService: BoatFormService,
    private http: HttpClient,
  ) {
    this.boatForm = this.boatFormService.createBoatForm();
  }

  addBoat(): void {
    if (this.boatForm.valid) {
      const newBoat: BoatRequest = this.boatForm.value;

      if (newBoat.registrationNumber === '') {
        newBoat.registrationNumber = null;
      }

      this.http.post<Boat>(`${environment.apiUrl}/boats`, newBoat).subscribe({
        next: (response: Boat) => {
          this.boatAdded.emit(response);
          this.boatForm.reset();
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
