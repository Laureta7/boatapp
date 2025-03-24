import {
  ComponentFixture,
  TestBed,
  fakeAsync,
  tick,
} from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '@app/services/auth-service.service';
import { Router } from '@angular/router';
import { of } from 'rxjs';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authServiceSpy: jasmine.SpyObj<AuthService>;
  let routerSpy: jasmine.SpyObj<Router>;

  beforeEach(async () => {
    authServiceSpy = jasmine.createSpyObj('AuthService', [
      'loginAndStore',
      'checkAuthenticationStatus',
    ]);
    routerSpy = jasmine.createSpyObj('Router', ['navigate']);

    // Mock checkAuthenticationStatus to return `false` by default
    authServiceSpy.checkAuthenticationStatus.and.returnValue(of(false));

    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, LoginComponent],
      providers: [
        { provide: AuthService, useValue: authServiceSpy },
        { provide: Router, useValue: routerSpy },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // Run change detection to initialize the component
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create a form with 2 controls', () => {
    expect(component.loginForm.contains('username')).toBeTruthy();
    expect(component.loginForm.contains('password')).toBeTruthy();
  });

  it('should require username and password', () => {
    const usernameControl = component.loginForm.get('username');
    const passwordControl = component.loginForm.get('password');

    // Ensure controls exist before setting their values
    if (usernameControl && passwordControl) {
      usernameControl.setValue(''); // Set to empty value
      passwordControl.setValue(''); // Set to empty value

      expect(usernameControl.valid).toBeFalsy(); // Username is required
      expect(passwordControl.valid).toBeFalsy(); // Password is required
    } else {
      fail('Form controls are not initialized properly');
    }
  });

  it('should call AuthService.loginAndStore when form is valid', () => {
    component.loginForm.setValue({
      username: 'testuser',
      password: 'password123',
    });

    component.login(new Event('submit'));

    expect(authServiceSpy.loginAndStore).toHaveBeenCalledWith(
      'testuser',
      'password123',
    );
  });

  it('should not call AuthService.loginAndStore when form is invalid', () => {
    component.loginForm.setValue({ username: '', password: '' });

    spyOn(console, 'error'); // Spy on console.error
    component.login(new Event('submit'));

    expect(authServiceSpy.loginAndStore).not.toHaveBeenCalled();
    expect(console.error).toHaveBeenCalledWith('Form is not valid');
  });

  it('should navigate to home route if authenticated', fakeAsync(() => {
    authServiceSpy.checkAuthenticationStatus.and.returnValue(of(true)); // Mocking authenticated status
    component.ngOnInit(); // Explicitly call ngOnInit
    tick(); // Simulate the passage of time for async operations

    expect(routerSpy.navigate).toHaveBeenCalledWith(['/']);
  }));

  it('should not navigate if not authenticated', fakeAsync(() => {
    component.ngOnInit(); // Call ngOnInit where the checkAuthentication happens
    tick(); // Simulate passage of time

    expect(routerSpy.navigate).not.toHaveBeenCalled();
  }));
});
