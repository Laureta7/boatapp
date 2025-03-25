import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {
  UbCardDescriptionDirective,
  UbCardDirective,
  UbCardContentDirective,
  UbCardFooterDirective,
  UbCardHeaderDirective,
  UbCardTitleDirective,
} from '@components/ui/card';
import { UbInputDirective } from '@components/ui/input';
import { ReactiveFormsModule } from '@angular/forms';
import { UbButtonDirective } from '@components/ui/button';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { AuthService } from '@app/services/auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true, // Mark the component as standalone
  imports: [
    CommonModule,
    UbCardDirective,
    UbCardHeaderDirective,
    UbCardTitleDirective,
    UbCardDescriptionDirective,
    UbCardContentDirective,
    UbCardFooterDirective,
    UbInputDirective,
    UbButtonDirective,
    ReactiveFormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  title = 'login';
  loginForm: FormGroup;

  private router = inject(Router);
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', Validators.required],
    });
  }
  ngOnInit(): void {
    this.authService.checkAuthenticationStatus().subscribe((authenticated) => {
      if (authenticated) {
        this.router.navigate(['/boats']);
      }
    });
  }

  login(event: Event) {
    event.preventDefault(); // Prevent page reload
    if (this.loginForm.valid) {
      const { username, password } = this.loginForm.value;
      this.authService.loginAndStore(username, password);
    } else {
      console.error('Form is not valid');
    }
  }
}
