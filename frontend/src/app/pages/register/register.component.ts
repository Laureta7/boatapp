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
import { CommonModule } from '@angular/common';
import { AuthService } from '@app/services/auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
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
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  title = 'Register';
  registerForm: FormGroup;
  private router = inject(Router);
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
  ) {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
  ngOnInit(): void {
    this.authService.checkAuthenticationStatus().subscribe((authenticated) => {
      if (authenticated) {
        this.router.navigate(['/boats']);
      }
    });
  }

  register(event: Event): void {
    event.preventDefault();
    if (this.registerForm.valid) {
      const { username, password } = this.registerForm.value;
      this.authService.register(username, password).subscribe({
        next: (response) => {
          console.log('Registration successful', response);
          this.router.navigate(['/login']);
        },
        error: (error) => {
          console.error('Registration error', error);
        },
      });
    }
  }
}
