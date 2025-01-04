import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Emisioncheck1Component } from './emisioncheck1.component';

describe('Emisioncheck1Component', () => {
  let component: Emisioncheck1Component;
  let fixture: ComponentFixture<Emisioncheck1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Emisioncheck1Component]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Emisioncheck1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
