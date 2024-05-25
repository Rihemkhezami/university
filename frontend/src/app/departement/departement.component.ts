import { Component, OnInit } from '@angular/core';
import { Departement } from '../_models/Departement'; // Importer le modèle de département
import { DepartementService } from '../_services/departement.service';
@Component({
  selector: 'app-departement', // Renommer le sélecteur
  templateUrl: './departement.component.html',
  styleUrls: ['./departement.component.css']
})
export class DepartementComponent implements OnInit { // Renommer la classe
  departements: Departement[] = []; // Changer le nom de la variable
  departementFormModel: Departement = { nom: '' }; // Changer le nom du modèle de formulaire
  isUpdating: boolean = false; // Garder le même indicateur de mise à jour

  constructor(private departementService: DepartementService) { } // Assurez-vous d'injecter le bon service

  ngOnInit(): void {
    this.loadDepartements(); // Changer l'appel à la méthode de chargement
  }

  loadDepartements(): void { // Changer le nom de la méthode
    this.departementService.getAllDepartements().subscribe(departements => {
      this.departements = departements;
    });
  }

  onAddDepartement(): void { // Changer le nom de la méthode
    this.departementService.createDepartement(this.departementFormModel).subscribe(() => {
      this.loadDepartements(); // Changer l'appel à la méthode de chargement
      this.resetDepartementForm(); // Changer l'appel à la méthode de réinitialisation
    });
  }

  deleteDepartement(id: number): void { // Changer le nom de la méthode
    this.departementService.deleteDepartement(id).subscribe(() => {
      this.departements = this.departements.filter(departement => departement.id !== id); // Changer le nom de la variable
    });
  }

  loadDepartementForUpdate(departement: Departement): void { // Changer le nom de la méthode
    this.departementFormModel = { ...departement }; // Changer le nom de la variable
    this.isUpdating = true; // Garder le même indicateur de mise à jour
  }

  onUpdateDepartement(): void { // Changer le nom de la méthode
    if (this.departementFormModel.id) {
      this.departementService.updateDepartement(this.departementFormModel.id, this.departementFormModel).subscribe(() => {
        this.loadDepartements(); // Changer l'appel à la méthode de chargement
        this.resetDepartementForm(); // Changer l'appel à la méthode de réinitialisation
      });
    }
  }

  cancelUpdate(): void { // Garder la méthode inchangée
    this.resetDepartementForm(); // Changer l'appel à la méthode de réinitialisation
  }

  resetDepartementForm(): void { // Changer le nom de la méthode
    this.departementFormModel = { nom: '' }; // Changer le nom de la variable
    this.isUpdating = false; // Garder le même indicateur de mise à jour
  }
}
