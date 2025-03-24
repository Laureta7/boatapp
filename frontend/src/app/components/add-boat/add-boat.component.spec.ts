import {
  ComponentFixture,
  fakeAsync,
  TestBed,
  tick,
} from '@angular/core/testing';
import { AddBoatComponent } from './add-boat.component'; // Make sure to point to the correct path
import { ReactiveFormsModule } from '@angular/forms';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClient, HttpHandler } from '@angular/common/http';
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
      providers: [HttpClient, HttpHandler], // Add any providers needed by the component
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
    // Define BoatRequest type for form input
    const newBoatRequest: BoatRequest = {
      name: 'Test Boat',
      ownerName: 'John Doe',
      description: 'A great boat',
      year: 2024,
      length: 10,
      price: 25000,
      registrationNumber: 'ABC12345',
    };

    // Set form values using BoatRequest
    component.boatForm.setValue(newBoatRequest);

    // Spy on the boatAdded emitter
    spyOn(component.boatAdded, 'emit');

    // Mock HTTP response returned after a successful POST request
    const mockResponse: Boat = {
      ...newBoatRequest, // Spread existing BoatRequest properties
      id: 'mock-uuid', // Add a mock ID to simulate response from backend
    };

    // Mock the POST request to return the mockResponse
    const httpClientMock = TestBed.inject(HttpClient);
    spyOn(httpClientMock, 'post').and.returnValue(of(mockResponse));

    // Call the method to add a boat
    component.addBoat();

    // Expect that boatAdded emitted the mock response (which is of type Boat)
    expect(component.boatAdded.emit).toHaveBeenCalledWith(mockResponse);
  });

  it('should reset the form when addBoat is successful', fakeAsync(() => {
    // Set the form values for testing
    component.boatForm.setValue({
      name: 'Test Boat',
      ownerName: 'John Doe',
      description: 'A great boat',
      year: 2024,
      length: 10,
      price: 25000,
      registrationNumber: 'ABC12345',
    });

    // Mock the successful HTTP response
    const mockResponse: Boat = {
      ...component.boatForm.value,
      id: 'mock-uuid', // Simulated backend ID response
    };

    const httpClientMock = TestBed.inject(HttpClient);
    spyOn(httpClientMock, 'post').and.returnValue(of(mockResponse)); // Mocking the post request

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
