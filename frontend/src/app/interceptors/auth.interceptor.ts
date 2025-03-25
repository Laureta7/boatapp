import {
  HttpInterceptorFn,
  HttpRequest,
  HttpHandlerFn,
  HttpEvent,
} from '@angular/common/http';
import { Observable } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn,
): Observable<HttpEvent<unknown>> => {
  // Get the CookieService instance

  // Clone the request and set the 'withCredentials' flag to true
  // This ensures that cookies are sent with the request
  req = req.clone({
    withCredentials: true, // ensure that credentials (cookies) are sent along with the request
  });

  // Pass the modified request to the next handler
  return next(req);
};
