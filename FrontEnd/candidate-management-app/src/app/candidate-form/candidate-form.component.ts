import { Component } from '@angular/core';
import { Candidate } from '../model/candidate.model';
import { CandidateService } from '../service/candidate.service';


@Component({
  selector: 'app-candidate-form',
  templateUrl: './candidate-form.component.html',
})
export class CandidateFormComponent {
  candidate: Candidate = {
    name: '',
    email: '',
    phoneNumber: '',
  };

  selectedFile: File | undefined;

  constructor(private candidateService: CandidateService) {}

  submitForm(): void {
    this.candidateService.addCandidate(this.candidate).subscribe();
  }

  onFileSelected(event: any): void {
    // Explicitly cast event.target to HTMLInputElement
    const inputElement = event.target as HTMLInputElement;

    // Check if files are present
    if (inputElement.files && inputElement.files.length > 0) {
      this.selectedFile = inputElement.files[0];
    }
  }

  uploadFile(): void {
    if (this.selectedFile) {
      this.candidateService.uploadCandidates(this.selectedFile).subscribe();
    }
  }
}
