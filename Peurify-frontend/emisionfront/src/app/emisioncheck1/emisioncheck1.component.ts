import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmissionCheckResponse, EmissionService } from '../emission-service.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-emisioncheck1',
  standalone: false,
  
  templateUrl: './emisioncheck1.component.html',
  styleUrl: './emisioncheck1.component.css'
})
export class Emisioncheck1Component {
 emissionForm: FormGroup;
  submitted = false;
  loading = false;
  errorMessage: string | null = null;
  successMessage: string | null = null;
  imagePreview: string | ArrayBuffer | null = null; 

  constructor(
    private fb: FormBuilder,
    private emissionCheckService: EmissionService,
    private reouter:ActivatedRoute,
    private router:Router
  ) {
    this.emissionForm = this.fb.group({
      numberPlate: ['', [Validators.required]],
      id: [this.reouter.snapshot.params['id']],
      image: [null, [Validators.required]]
    });
  }

  // Getter for easy access to form controls
  get f() { return this.emissionForm.controls; }

  onFileChange(event: any) {
    if (event.target.files && event.target.files.length > 0) {
      const file = event.target.files[0];
      this.emissionForm.patchValue({ image: file });

      // Generate image preview
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result; // Store the base64 image data
      };
      reader.readAsDataURL(file); // Convert the file to base64 format
    }
  }


  submitForm() {
    this.submitted = true;
    this.errorMessage = null;
    this.successMessage = null;

    if (this.emissionForm.invalid) {
      return;
    }

    this.loading = true;

    const { numberPlate, id, image } = this.emissionForm.value;

    this.emissionCheckService.checkEmission(numberPlate, image, id).subscribe({
      next: (response: EmissionCheckResponse) => {
        if (response.status === 'ERROR') {
          this.errorMessage = response.message;
        } else {
          if(response.status=="PASS"){
            this.successMessage = `Emission check completed successfully! However, rewards cannot be granted as this vehicle doesn't have emission requirements`;
          }
          else{
            this.successMessage = `Emission check successful! Status: ${response.status}, & You got 100 Rewards`;
          }
          
        }
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'You are unable to submit a complaint for this vehicle as it is already in the queue.';
        this.loading = false;
      }
    });
  }

  myprofile(){
    console.log(this.reouter.snapshot.params['id'])
    this.router.navigate(['usersdetails',this.reouter.snapshot.params['id']]);
  
   }
}