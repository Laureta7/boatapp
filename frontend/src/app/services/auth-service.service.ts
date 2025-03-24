import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { BehaviorSubject, catchError, map, Observable, of } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

export interface LoginResponse {
  message: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.apiUrl;
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

  public isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  cookieService = inject(CookieService);

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {}

  // Log in method to post credentials and receive response with headers
  login(
    username: string,
    password: string,
  ): Observable<HttpResponse<LoginResponse>> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true,
      observe: 'response' as const,
    };

    return this.http.post<LoginResponse>(
      `${this.apiUrl}/users/login`,
      { username, password },
      httpOptions,
    );
  }

  // Handle login processing and manage authenticated state
  loginAndStore(username: string, password: string): void {
    this.login(username, password).subscribe({
      next: () => {
        // The cookie is set automatically by the backend in the response
        this.isAuthenticatedSubject.next(true);
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Login failed', error);
        this.isAuthenticatedSubject.next(false);
      },
    });
  }

  checkAuthenticationStatus(): Observable<boolean> {
    return this.http
      .get(`${this.apiUrl}/users/verify-token`, { withCredentials: true })
      .pipe(
        map(() => {
          this.isAuthenticatedSubject.next(true);
          return true;
        }),
        catchError(() => {
          this.isAuthenticatedSubject.next(false);
          return of(false);
        }),
      );
  }

  // Logout logic
  logout(): void {
    // Handle server-side logout if required, or just navigate
    this.isAuthenticatedSubject.next(false);
    this.router.navigate(['/login']); // Redirect to login page
  }
}
