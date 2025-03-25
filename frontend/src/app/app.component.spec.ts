import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { AuthService } from './services/auth-service.service';
import { of, Subscription } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let authServiceMock: jasmine.SpyObj<AuthService>;

  beforeEach(async () => {
    // Create a mock object for AuthService
    authServiceMock = jasmine.createSpyObj('AuthService', ['logout']);
    authServiceMock.isAuthenticated$ = of(false); // Provide initial value for authentication status

    await TestBed.configureTestingModule({
      imports: [AppComponent], // Import the standalone component here
      providers: [
        { provide: AuthService, useValue: authServiceMock }, // Provide the mock service
        CookieService, // Provide the CookieService if used in the component
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // Run change detection
  });

  it('should create the app', () => {
    expect(component).toBeTruthy();
  });

  it('should subscribe to authentication status', () => {
    component.ngOnInit();
    expect(component.isAuthenticated).toBe(false);

    // Simulate authentication
    authServiceMock.isAuthenticated$ = of(true);
    component.ngOnInit(); // Trigger ngOnInit again to subscribe to the new value
    expect(component.isAuthenticated).toBe(true);
  });

  it('should call logout on AuthService', () => {
    component.logout();
    expect(authServiceMock.logout).toHaveBeenCalled();
  });

  it('should unsubscribe on destroy', () => {
    // Create a spy for the subscription's unsubscribe method
    const unsubscribeSpy = spyOn(Subscription.prototype, 'unsubscribe');

    component.ngOnInit(); // Initialize and subscribe
    component.ngOnDestroy(); // Call ngOnDestroy to unsubscribe

    // Check if unsubscribe was called
    expect(unsubscribeSpy).toHaveBeenCalled();
  });
});
