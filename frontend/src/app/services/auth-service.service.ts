import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment'; // Adjust the path if necessary
import { BehaviorSubject, Observable } from 'rxjs';

export interface LoginResponse {
  token: string; // Define the expected shape of the response from the login endpoint
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.apiUrl; // URL for your API
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(
    this.hasToken(),
  );
  public isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {}

  // Log in and store the JWT token
  login(username: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/users/login`, {
      username,
      password,
    });
  }

  // Call this method after a successful login to set the token and notify observers
  storeToken(token: string): void {
    localStorage.setItem('token', token); // Store the JWT in local storage
    this.isAuthenticatedSubject.next(true); // Notify that the user is authenticated
  }

  // Get the token from local storage
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // Check if the user is authenticated
  isAuthenticated(): boolean {
    return this.hasToken();
  }

  // Logout the user
  logout(): void {
    localStorage.removeItem('token');
    this.isAuthenticatedSubject.next(false); // Notify that the user is no longer authenticated
    this.router.navigate(['/login']); // Redirect to the login page
  }

  // Helper method to check if a token exists
  private hasToken(): boolean {
    return !!this.getToken();
  }
}
