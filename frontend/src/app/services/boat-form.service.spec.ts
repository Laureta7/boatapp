import { TestBed } from '@angular/core/testing';

import { BoatFormService } from './boat-form.service';

describe('BoatFormService', () => {
  let service: BoatFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoatFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
