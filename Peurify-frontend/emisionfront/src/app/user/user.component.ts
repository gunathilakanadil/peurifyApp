import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user',
  standalone: false,
  
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit{
  user:User=new User();

  constructor(private userservice:UserService,private router:Router){}
  ngOnInit(): void {
    // Any initialization logic can go here.
  }

  onSubmit(): void {
    console.log(this.user);
    this.saveUser(); // Save the customer
  }

  saveUser(): void {
    this.userservice.createUser(this.user).subscribe((response: User) => {
        // Assuming the server returns the customer with an `id`.
        this.user = response;
        console.log("ID IS:", response.id);
        this.router.navigate(['emisioncheck1',response.id]);

         
    }, error => {
        console.error('Error creating customer:', error);
    });
}

  
}


