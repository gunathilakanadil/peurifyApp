import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { EmissionCheckComponentComponent } from './emission-check-component-component';
import { Emisioncheck1Component } from './emisioncheck1/emisioncheck1.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { CvComponent } from './cv/cv.component';

const routes: Routes = [
  { path: '', redirectTo: 'emisioncheck1/10', pathMatch: 'full' }, // Add this for empty path
  { path: 'users', component: UserComponent }, // Add this for base users route
  { path: 'users/:id', component: UserComponent },
  { path: 'usersdetails/:id', component: UserDetailsComponent },
  { path: 'updateusersdetails/:id', component: UpdateUserComponent },
  { path: 'emisioncheck', component: EmissionCheckComponentComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'cv', component: CvComponent },


  { path: 'emisioncheck1/:id', component: Emisioncheck1Component },
  { path: '**', redirectTo: 'home' } // Remove pathMatch: 'full' from wildcard
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }