import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';

export interface LoginResponse {
  message: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.apiUrl;
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(
    this.hasToken(),
  );
  public isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {}

  // Log in and get response including headers
  login(
    username: string,
    password: string,
  ): Observable<HttpResponse<LoginResponse>> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true, // Necessary for cookie handling
      observe: 'response' as 'response', // Get full response
    };

    return this.http.post<LoginResponse>(
      `${this.apiUrl}/users/login`,
      { username, password },
      httpOptions,
    );
  }

  // Handle login processing
  loginAndStore(username: string, password: string): void {
    this.login(username, password).subscribe({
      next: (response) => {
        console.log('Response from server:', response);
        console.log('Response headers:', response.headers.keys());

        // Check for cookie presence in headers (not needed if backend sets it correctly)
        // Instead of manually parsing response, you rely on servlet's response to send cookie automatically
        this.isAuthenticatedSubject.next(true);
        this.router.navigate(['/dashboard']); // Navigate on successful login
      },
      error: (error) => {
        console.error('Login failed', error); // Handle errors
        this.isAuthenticatedSubject.next(false);
      },
    });
  }

  // Check authentication status
  isAuthenticated(): boolean {
    return !!document.cookie
      .split('; ')
      .find((row) => row.startsWith('token=')); // Check if token cookie exists
  }

  hasToken(): boolean {
    return !!document.cookie
      .split('; ')
      .find((row) => row.startsWith('token=')); // Check if token cookie exists
  }

  // Logout logic: delete cookie, redirect, etc.
}
