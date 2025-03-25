import { TestBed } from '@angular/core/testing';
import { AuthService } from '@services/auth-service.service'; // Adjust the import path as necessary
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { environment } from '../../environments/environment';

import { LoginResponse } from '@interfaces/login-response';
describe('AuthService', () => {
  let service: AuthService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;
  let routerSpy: jasmine.SpyObj<Router>;
  let cookieServiceSpy: jasmine.SpyObj<CookieService>;

  beforeEach(() => {
    // Create spy objects for dependencies
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['post', 'get']);
    routerSpy = jasmine.createSpyObj('Router', ['navigate']);
    cookieServiceSpy = jasmine.createSpyObj('CookieService', [
      'get',
      'set',
      'delete',
    ]);

    TestBed.configureTestingModule({
      providers: [
        AuthService,
        { provide: HttpClient, useValue: httpClientSpy }, // Provide the mocked HttpClient
        { provide: Router, useValue: routerSpy }, // Provide the mocked Router
        { provide: CookieService, useValue: cookieServiceSpy }, // Provide the mocked CookieService
      ],
    });

    service = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('login', () => {
    it('should call the login API and return response', () => {
      const mockResponse = { message: 'Login successful' };
      const username = 'testuser';
      const password = 'testpassword';

      // Mock successful response with a full HttpResponse
      httpClientSpy.post.and.returnValue(
        of(
          new HttpResponse<LoginResponse>({
            body: mockResponse,
            status: 200,
            statusText: 'OK',
            headers: new HttpHeaders(),
            url: `${environment.apiUrl}/users/login`,
          }),
        ),
      );

      service.login(username, password).subscribe((response) => {
        expect(response.body).toEqual(mockResponse); // Check the body of the response
      });

      expect(httpClientSpy.post).toHaveBeenCalledWith(
        `${environment.apiUrl}/users/login`,
        { username, password },
        {
          headers: jasmine.any(Object),
          withCredentials: true,
          observe: 'response' as unknown as 'body',
        },
      );
    });

    it('should handle errors gracefully', () => {
      const username = 'testuser';
      const password = 'testpassword';

      httpClientSpy.post.and.returnValue(throwError(() => 'Login failed')); // Mock error response

      service.login(username, password).subscribe({
        next: () => fail('expected an error, not a response'),
        error: (error) => {
          expect(error).toEqual('Login failed'); // Ensure the error was handled
        },
      });
    });
  });

  describe('loginAndStore', () => {
    it('should navigate to boats on successful login', () => {
      const username = 'testuser';
      const password = 'testpassword';
      const mockResponse = { message: 'Login successful' };

      httpClientSpy.post.and.returnValue(of(mockResponse)); // Mock successful response

      service.loginAndStore(username, password); // Call the method

      // Vérifier l'état d'authentification via l'observable public
      service.isAuthenticated$.subscribe((isAuthenticated) => {
        expect(isAuthenticated).toBe(true); // Check authentication status
      });
      expect(routerSpy.navigate).toHaveBeenCalledWith(['/boats']); // Ensure navigation occurs
    });

    it('should handle login failure', () => {
      const username = 'testuser';
      const password = 'testpassword';

      httpClientSpy.post.and.returnValue(throwError(() => 'Login failed')); // Mock error response

      service.loginAndStore(username, password); // Call the method

      // Vérifier l'état d'authentification via l'observable public
      service.isAuthenticated$.subscribe((isAuthenticated) => {
        expect(isAuthenticated).toBe(false); // Check authentication status
      });
    });
  });

  describe('checkAuthenticationStatus', () => {
    it('should return true if the user is authenticated', () => {
      httpClientSpy.get.and.returnValue(of({})); // Mock successful verification response

      service.checkAuthenticationStatus().subscribe((isAuthenticated) => {
        expect(isAuthenticated).toBe(true);
        // Vérifier l'état d'authentification via l'observable public
        service.isAuthenticated$.subscribe((authState) => {
          expect(authState).toBe(true); // Update state
        });
      });
    });

    it('should return false and update state if verification fails', () => {
      httpClientSpy.get.and.returnValue(
        throwError(() => 'Token verification failed'),
      ); // Mock error response

      service.checkAuthenticationStatus().subscribe((isAuthenticated) => {
        expect(isAuthenticated).toBe(false);
        // Vérifier l'état d'authentification via l'observable public
        service.isAuthenticated$.subscribe((authState) => {
          expect(authState).toBe(false); // Update state
        });
      });
    });
  });

  describe('logout', () => {
    it('should call logout API and navigate to login', () => {
      const mockResponse = { message: 'Logout successful' };

      httpClientSpy.post.and.returnValue(of(mockResponse)); // Mock successful logout response

      service.logout(); // Call the logout method

      expect(httpClientSpy.post).toHaveBeenCalledWith(
        `${environment.apiUrl}/users/logout`,
        {},
        { withCredentials: true },
      ); // Ensure the logout API is called
      // Vérifier l'état d'authentification via l'observable public
      service.isAuthenticated$.subscribe((isAuthenticated) => {
        expect(isAuthenticated).toBe(false); // Ensure authentication state is updated
      });
      expect(routerSpy.navigate).toHaveBeenCalledWith(['/login']); // Ensure navigation occurs
    });
  });
});
