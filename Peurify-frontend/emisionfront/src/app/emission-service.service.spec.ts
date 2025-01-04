import { TestBed } from '@angular/core/testing';

import { EmissionServiceService } from './emission-service.service';

describe('EmissionServiceService', () => {
  let service: EmissionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmissionServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
