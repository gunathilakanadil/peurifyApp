import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

// Interface for the response
export interface EmissionCheckResponse {
  status: string;
  message: string;
  data?: any; // Adjust this based on the actual structure of `EmissionCheckResponse`
}

@Injectable({
  providedIn: 'root'
})
export class EmissionService {
  private readonly baseUrl = 'http://localhost:8080/api/vehicles'; // Update with your actual API base URL

  constructor(private http: HttpClient) {}

  checkEmission(numberPlate: string, image: File, id: number): Observable<EmissionCheckResponse> {
    const url = `${this.baseUrl}/check-emission`;

    // FormData to handle multipart file uploads
    const formData = new FormData();
    formData.append('numberPlate', numberPlate);
    formData.append('image', image);
    formData.append('id', id.toString());

    return this.http.post<EmissionCheckResponse>(url, formData).pipe(
      catchError(this.handleError)

    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // Client-side or network error
      console.error('An error occurred:', error.error.message);
    } else {
      // Backend error
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // Return observable with a user-facing error message
    return throwError('Something went wrong; please try again later.');
  }
}