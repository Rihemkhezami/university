import { Component, OnInit } from '@angular/core';
import { Classe } from '../_models/Classe';
import { ClasseService } from '../_services/classe.service';
@Component({
  selector: 'app-classes',
  templateUrl: './classes.component.html',
  styleUrl: './classes.component.css'
})
export class ClassesComponent implements OnInit{
  classes: Classe[] = []; // Modifier le type de matieres à classes
  classeFormModel: Classe = { nom: '' };  // Modèle du formulaire de classe
  isUpdating: boolean = false;  // Indicateur de mise à jour

  constructor(private classeService: ClasseService) { } // Modifier le service à classeService

  ngOnInit(): void {
    this.loadClasses(); // Modifier la méthode loadMatieres à loadClasses
  }

  loadClasses(): void { // Modifier la signature de la méthode
    this.classeService.getAllClasses().subscribe(classes => { // Modifier la méthode à getAllClasses
      this.classes = classes; // Modifier la propriété à classes
    });
  }

  onAddClasse(): void { // Modifier la signature de la méthode
    this.classeService.createClasse(this.classeFormModel).subscribe(() => { // Modifier la méthode à createClasse
      this.loadClasses();  // Modifier la méthode à loadClasses
      this.resetForm();  // Modifier la méthode à resetForm
    });
  }

  deleteClasse(id: number): void { // Modifier la signature de la méthode
    this.classeService.deleteClasse(id).subscribe(() => { // Modifier la méthode à deleteClasse
      this.classes = this.classes.filter(classe => classe.id !== id);  // Modifier la méthode à classes
    });
  }

  loadClasseForUpdate(classe: Classe): void { // Modifier la signature de la méthode
    this.classeFormModel = { ...classe };  // Modifier la méthode à classeFormModel
    this.isUpdating = true;  // Modifier la méthode à isUpdating
  }

  onUpdateClasse(): void { // Modifier la signature de la méthode
    if (this.classeFormModel.id) { // Modifier la méthode à classeFormModel
      this.classeService.updateClasse(this.classeFormModel.id, this.classeFormModel).subscribe(() => { // Modifier la méthode à updateClasse
        this.loadClasses();  // Modifier la méthode à loadClasses
        this.resetForm();  // Modifier la méthode à resetForm
      });
    }
  }

  cancelUpdate(): void { // Modifier la signature de la méthode
    this.resetForm();  // Modifier la méthode à resetForm
  }

  resetForm(): void { // Modifier la signature de la méthode
    this.classeFormModel = { nom: '' };  // Modifier la méthode à classeFormModel
    this.isUpdating = false;  // Modifier la méthode à isUpdating
  }
}
