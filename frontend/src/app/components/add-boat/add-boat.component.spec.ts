import {
  ComponentFixture,
  fakeAsync,
  TestBed,
  tick,
} from '@angular/core/testing';
import { AddBoatComponent } from './add-boat.component'; // Make sure to point to the correct path
import { ReactiveFormsModule } from '@angular/forms';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { Boat } from '@interfaces/boat'; // Import your interface correctly based on its location
import { BoatRequest } from '@app/interfaces/boat-request';
import { of } from 'rxjs';

describe('AddBoatComponent', () => {
  let component: AddBoatComponent;
  let fixture: ComponentFixture<AddBoatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        AddBoatComponent, // Import the standalone component here
        ReactiveFormsModule,
      ],
      providers: [provideHttpClient(), provideHttpClientTesting()], // Add any providers needed by the component
      schemas: [NO_ERRORS_SCHEMA], // If you have unknown components/directives, you can use this
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBoatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // Run change detection
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a valid form when valid input is provided', () => {
    component.boatForm.setValue({
      name: 'Test Boat',
      ownerName: 'John Doe',
      description: 'A great boat',
      year: 2024,
      length: 10,
      price: 25000,
      registrationNumber: 'ABC12345',
    });

    expect(component.boatForm.valid).toBeTruthy();
  });

  it('should emit boatAdded when addBoat is called with a valid form', () => {
    const newBoatRequest: BoatRequest = {
      name: 'Test Boat',
      ownerName: 'John Doe',
      description: 'A great boat',
      year: 2024,
      length: 10,
      price: 25000,
      registrationNumber: 'ABC12345',
    };

    component.boatForm.setValue(newBoatRequest);
    spyOn(component.boatAdded, 'emit'); // Spy on the emit method

    // Mock HTTP response if the actual HTTP call is being made
    const mockResponse: Boat = {
      ...newBoatRequest, // Spread existing BoatRequest properties
      id: 'mock-uuid', // Add a mock ID to simulate response from backend
    };

    // Mock the HTTP call to return the expected response
    spyOn(component['http'], 'post').and.returnValue(of(mockResponse)); // Ensure the HTTP service returns the expected observable

    // Call the method to add a boat
    component.addBoat();

    // Expect that boatAdded emitted the mock response
    expect(component.boatAdded.emit).toHaveBeenCalledWith(mockResponse);
  });

  it('should reset the form when addBoat is successful', fakeAsync(() => {
    component.boatForm.setValue({
      name: 'Test Boat',
      ownerName: 'John Doe',
      description: 'A great boat',
      year: 2024,
      length: 10,
      price: 25000,
      registrationNumber: 'ABC12345',
    });

    const mockResponse: Boat = {
      name: 'Test Boat',
      ownerName: 'John Doe',
      description: 'A great boat',
      year: 2024,
      length: 10,
      price: 25000,
      registrationNumber: 'ABC12345',
      id: 'mock-uuid', // Mocking the response
    };

    // Mock the HTTP call
    spyOn(component['http'], 'post').and.returnValue(of(mockResponse));

    component.addBoat(); // Call the method to add a boat
    tick(); // Simulate the passage of time for the asynchronous call to complete

    // Check if the form resets after adding a boat
    expect(component.boatForm.value).toEqual({
      name: null,
      ownerName: null,
      description: null,
      year: null,
      length: null,
      price: null,
      registrationNumber: null,
    });
  }));
});
