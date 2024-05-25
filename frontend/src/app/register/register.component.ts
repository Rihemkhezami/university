import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { Observable } from 'rxjs';
import { DataService } from '../_services/data.service';
import { Classe } from '../_models/Classe';
import { Departement } from '../_models/Departement';
import { Matiere } from '../_models/Matiere';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {
    username: null,
    email: null,
    password: null,
    role: 'etudiant',
    matieres: null,
    classes: null,
    departements: null,
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  // Propriétés pour stocker les listes
  matieres: Matiere[] = [];
  classes: Classe[] = [];
  departements: Departement[] = [];

  constructor(private authService: AuthService, private dataService: DataService) { }

  ngOnInit(): void {
    // Charger les listes depuis le backend
    this.dataService.getMatieres().subscribe(
     { next: data => {this.matieres = data}}
    );
    this.dataService.getClasses().subscribe(
     { next: data => {this.classes = data}}
    );
    this.dataService.getDepartements().subscribe(
     {next: data =>{ this.departements = data}}
    );
  }

  onSubmit(): void {
    const { username, email, password, matieres, classes, departements } = this.form;

    this.authService.register(username, email, password, matieres, classes, departements).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
  }
}
