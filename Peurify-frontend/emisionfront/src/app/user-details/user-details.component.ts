import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-details',
  standalone: false,
  
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.css'
})
export class UserDetailsComponent implements OnInit {
  user:User=new User();
  error: string | null = null;
  constructor(private route:ActivatedRoute,private router:Router,private userservice:UserService){

  }

  ngOnInit(): void {
    const customerId = this.route.snapshot.paramMap.get('id');
    if (customerId) {
      this.fetchuserdetails(+customerId);
       
    }
    
  }
  fetchuserdetails(id:number){
    this.userservice.getUserByID(id).subscribe(
      (data) => {
        this.user = data;
        console.log(this.user)
        this.error = null;
      },
      (err) => {
        this.error = 'Failed to fetch customer details';
        console.error(err);
      }
    );
    
  }

  myprofile(id:number){
    this.router.navigate(['usersdetails',id]);
  
   }

  update(id:number){
    this.router.navigate(['updateusersdetails',id]);
  }
}
