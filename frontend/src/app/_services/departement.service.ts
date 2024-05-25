import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Departement } from '../_models/Departement'; // Importer le modèle de département

@Injectable({
  providedIn: 'root'
})
export class DepartementService { // Renommer le service
  private apiUrl = 'http://localhost:8090/api/departements'; // Changer l'URL de l'API pour correspondre à la gestion des départements

  constructor(private http: HttpClient) { }

  getAllDepartements(): Observable<Departement[]> { // Changer le nom de la méthode
    return this.http.get<Departement[]>(this.apiUrl);
  }

  createDepartement(departement: Departement): Observable<Departement> { // Changer le nom de la méthode
    return this.http.post<Departement>(this.apiUrl, departement);
  }

  updateDepartement(id: number, departement: Departement): Observable<Departement> { // Changer le nom de la méthode
    return this.http.put<Departement>(`${this.apiUrl}/${id}`, departement);
  }

  deleteDepartement(id: number): Observable<void> { // Changer le nom de la méthode
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
