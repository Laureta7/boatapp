import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';
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

  // Login processing and authenticated state management
  loginAndStore(username: string, password: string): void {
    this.login(username, password).subscribe({
      next: (response) => {
        console.log('Login successful:', response);
        this.isAuthenticatedSubject.next(true);
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Login failed', error);
        this.isAuthenticatedSubject.next(false);
      },
    });
  }

  // Check JWT token validity
  checkAuthenticationStatus(): void {
    this.http
      .get(`${this.apiUrl}/verify-token`, {
        withCredentials: true,
        observe: 'response',
      })
      .subscribe({
        next: (response) => {
          if (response.status === 200) {
            this.isAuthenticatedSubject.next(true);
          } else {
            this.logout();
          }
        },
        error: (error) => {
          console.error('Erreur de vérification du token :', error);
          this.logout();
        },
      });
  }

  logout(): void {
    this.cookieService.delete('token');
    this.isAuthenticatedSubject.next(false);
    this.router.navigate(['/login']);
  }
}
