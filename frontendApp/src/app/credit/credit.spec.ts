import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Credit } from './credit';

describe('Credit', () => {
  let component: Credit;
  let fixture: ComponentFixture<Credit>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Credit],
    }).compileComponents();

    fixture = TestBed.createComponent(Credit);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
