import { Injectable, inject } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { Observable, of } from 'rxjs';
import { AuthService } from '@services/auth-service.service';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  private authService = inject(AuthService);
  private router = inject(Router);

  canActivate(): Observable<boolean | UrlTree> {
    return this.authService.checkAuthenticationStatus().pipe(
      map((authenticated) => {
        if (authenticated) {
          return true; // Valid token, access authorized
        } else {
          return this.router.createUrlTree(['/login']);
        }
      }),
      catchError(() => {
        return of(this.router.createUrlTree(['/login']));
      }),
    );
  }
}
