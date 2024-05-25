import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListmatiereComponent } from './listmatiere.component';

describe('ListmatiereComponent', () => {
  let component: ListmatiereComponent;
  let fixture: ComponentFixture<ListmatiereComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListmatiereComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListmatiereComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
