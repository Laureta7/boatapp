import { Component, OnInit, OnDestroy } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UbButtonDirective } from '@components/ui/button';
import { AuthService } from './services/auth-service.service';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, UbButtonDirective],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'frontend';
  isAuthenticated: boolean = false;
  private subscription!: Subscription;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    // Subscribe to the authentication status
    this.subscription = this.authService.isAuthenticated$.subscribe(
      (authStatus) => {
        this.isAuthenticated = authStatus;
      },
    );
  }

  logout(): void {
    this.authService.logout();
  }

  ngOnDestroy(): void {
    // Subscriptions can lead to memory leaks
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
