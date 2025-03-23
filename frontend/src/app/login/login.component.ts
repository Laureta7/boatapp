import { Component } from '@angular/core';
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
    ReactiveFormsModule, // Import ReactiveFormsModule here
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'], // Ensure this is also corrected
})
export class LoginComponent {
  title = 'login';
  loginForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]], // Email field with validations
      password: ['', Validators.required], // Password field with validation
    });
  }

  login(event: Event) {
    event.preventDefault(); // Prevent page reload
    if (this.loginForm.valid) {
      console.log('Email:', this.loginForm.value.email);
      console.log('Password:', this.loginForm.value.password);
    } else {
      console.error('Form is not valid');
    }
  }
}
