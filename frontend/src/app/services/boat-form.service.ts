import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class BoatFormService {
  constructor(private fb: FormBuilder) {}

  createBoatForm(): FormGroup {
    return this.fb.group({
      name: [
        '',
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(255),
        ],
      ],
      description: [
        '',
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(1000),
        ],
      ],
      year: [
        '',
        [
          Validators.required,
          Validators.min(1900),
          Validators.max(new Date().getFullYear()),
        ],
      ],
      length: ['', [Validators.required, Validators.min(0)]],
      ownerName: [
        '',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(255),
        ],
      ],
      price: ['', [Validators.required, Validators.min(0)]],
      registrationNumber: [
        '',
        [Validators.minLength(5), Validators.maxLength(20)],
      ],
    });
  }
}
