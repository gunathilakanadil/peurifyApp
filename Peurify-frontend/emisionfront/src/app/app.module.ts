import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UpdateUserComponent } from './update-user/update-user.component';
 
import { Emisioncheck1Component } from './emisioncheck1/emisioncheck1.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { CvComponent } from './cv/cv.component';
 

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UserDetailsComponent,
    UpdateUserComponent,
    
    Emisioncheck1Component,
          HomeComponent,
          LoginComponent,
          CvComponent,
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [], // Adjust if additional providers are required.
  bootstrap: [AppComponent]
})
export class AppModule { }
