import { Component } from '@angular/core';

@Component({
  selector: 'app-cv',
  standalone: false,
  
  templateUrl: './cv.component.html',
  styleUrl: './cv.component.css'
})
export class CvComponent {
  showPopup: boolean = false;

}
