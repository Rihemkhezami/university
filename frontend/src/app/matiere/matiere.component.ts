import { Component, OnInit } from '@angular/core';
import { Matiere } from '../_models/Matiere';
import { MatiereService } from '../_services/matiere.service';

@Component({
  selector: 'app-matiere',
  templateUrl: './matiere.component.html',
  styleUrls: ['./matiere.component.css']
})
export class MatiereComponent implements OnInit {
  matieres: Matiere[] = [];
  matiereFormModel: Matiere = { nom: '' };  // Modèle du formulaire de matière
  isUpdating: boolean = false;  // Indicateur de mise à jour

  constructor(private matiereService: MatiereService) { }

  ngOnInit(): void {
    this.loadMatieres();
  }

  loadMatieres(): void {
    this.matiereService.getAllMatieres().subscribe(matieres => {
      this.matieres = matieres;
    });
  }

  onAddMatiere(): void {
    this.matiereService.createMatiere(this.matiereFormModel).subscribe(() => {
      this.loadMatieres();  // Recharger la liste des matières après ajout
      this.resetForm();  // Réinitialiser le formulaire
    });
  }

  deleteMatiere(id: number): void {
    this.matiereService.deleteMatiere(id).subscribe(() => {
      this.matieres = this.matieres.filter(matiere => matiere.id !== id);  // Supprimer la matière de la liste
    });
  }

  loadMatiereForUpdate(matiere: Matiere): void {
    this.matiereFormModel = { ...matiere };  // Charger la matière dans le formulaire
    this.isUpdating = true;  // Activer le mode mise à jour
  }

  onUpdateMatiere(): void {
    if (this.matiereFormModel.id) {
      this.matiereService.updateMatiere(this.matiereFormModel.id, this.matiereFormModel).subscribe(() => {
        this.loadMatieres();  // Recharger la liste des matières après mise à jour
        this.resetForm();  // Réinitialiser le formulaire
      });
    }
  }

  cancelUpdate(): void {
    this.resetForm();  // Réinitialiser le formulaire
  }

  resetForm(): void {
    this.matiereFormModel = { nom: '' };  // Réinitialiser le modèle de formulaire
    this.isUpdating = false;  // Désactiver le mode mise à jour
  }
}
