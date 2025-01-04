import { Component } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  standalone: false,
  
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {phoneNumber: string = '';
  password: string = '';
  message: string = '';

  constructor(private userservice: UserService) {}

  onLogin(): void {
    if (this.phoneNumber && this.password) {
      this.userservice.login(this.phoneNumber, this.password).subscribe(
        (response: string) => {
          this.message = response; // Login successful message
            
        },
        (error) => {
          this.message =
            error.status === 401
              ? 'Invalid credentials'
              : 'An error occurred. Please try again.';
        }
      );
    } else {
      this.message = 'Please fill out all fields.';
    }
  }
}