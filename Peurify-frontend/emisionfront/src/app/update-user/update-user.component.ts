import { Component } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-user',
  standalone: false,
  
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.css'
})
export class UpdateUserComponent {
  user:User=new User();
  id: number=0;
  
  
  constructor( private uservice: UserService, private route: ActivatedRoute,private routes:Router) {}


  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    
 
    this.uservice.getUserByID(this.id).subscribe(data => {
      this.user = data as User;
        
      
    }, error => {
      console.log(error);
    });

     
  }

  onSubmit(){
     
    this.uservice.updateUser(this.id, this.user).subscribe(data=>{
      
       
      this.goTouserdetails();
    })
 
}

goTouserdetails(){
  this.routes.navigate(['usersdetails',this.id]);
 }

 myprofile(id:number){
  this.routes.navigate(['usersdetails',this.id]);

 }
}
