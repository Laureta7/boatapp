import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BoatDetailComponent } from './boat-detail.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';

describe('BoatDetailComponent', () => {
  let component: BoatDetailComponent;
  let fixture: ComponentFixture<BoatDetailComponent>;

  beforeEach(async () => {
    const mockActivatedRoute = {
      snapshot: {
        paramMap: {
          get: () => '1', // Simulate getting a boat ID from route parameters
        },
      },
    };

    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        BoatDetailComponent, // Import the standalone component here
      ],
      providers: [
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
        {
          provide: Router,
          useValue: { navigate: jasmine.createSpy('navigate') },
        },
        provideHttpClient(),
        provideHttpClientTesting(),
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(BoatDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load boat details on initialization', () => {
    spyOn(component, 'getBoatDetails').and.callThrough();
    component.ngOnInit(); // Call ngOnInit to trigger loading of boat details
    expect(component.getBoatDetails).toHaveBeenCalledWith('1'); // Ensure the method was called with the mock ID
  });
});
