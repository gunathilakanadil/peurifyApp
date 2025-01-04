 

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseURL="http://localhost:8080/api/users";
  constructor(private httpClient:HttpClient) { }

  createUser(user:User):Observable<User>{
    return this.httpClient.post<User>(`${this.baseURL}`, user);
  }
   
   
  getUserByID(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.baseURL}/${id}`);  // Fixed mismatched quotes here
  }

  updateUser(id:number,user:User):Observable<Object>{
  return this.httpClient.put(`${this.baseURL}/${id}`,user);  
}

deleteUser(id:number):Observable<Object>{
  return this.httpClient.delete(`${this.baseURL}/${id}`); 
}

login(phoneNumber: string, password: string): Observable<string> {
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  const body = { phoneNumber, password };

  return this.httpClient.post<string>(this.baseURL, body, { headers });
}
}
