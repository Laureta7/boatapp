import { TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { BoatFormService } from './boat-form.service'; // Adjust import path as necessary

describe('BoatFormService', () => {
  let service: BoatFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BoatFormService, FormBuilder],
    });
    service = TestBed.inject(BoatFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should create a boat form with the correct structure', () => {
    const form = service.createBoatForm();

    expect(form).toBeTruthy(); // Ensure the form is created

    // Check that form controls exist
    expect(form.contains('name')).toBeTruthy();
    expect(form.contains('description')).toBeTruthy();
    expect(form.contains('year')).toBeTruthy();
    expect(form.contains('length')).toBeTruthy();
    expect(form.contains('ownerName')).toBeTruthy();
    expect(form.contains('price')).toBeTruthy();
    expect(form.contains('registrationNumber')).toBeTruthy();
  });

  it('should be invalid when fields are empty', () => {
    const form = service.createBoatForm();
    form.setValue({
      name: '',
      description: '',
      year: '',
      length: '',
      ownerName: '',
      price: '',
      registrationNumber: '',
    });

    expect(form.valid).toBeFalse();
  });

  it('should be valid when all fields are correctly filled', () => {
    const form = service.createBoatForm();
    form.setValue({
      name: 'Test Boat',
      description: 'A great boat',
      year: 2023,
      length: 10,
      ownerName: 'John Doe',
      price: 25000,
      registrationNumber: 'REG12345',
    });

    expect(form.valid).toBeTrue();
  });
});
