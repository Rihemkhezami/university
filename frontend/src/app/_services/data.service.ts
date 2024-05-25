import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Classe } from '../_models/Classe';
import { Departement } from '../_models/Departement';
import { Matiere } from '../_models/Matiere';


@Injectable({
  providedIn: 'root',
})
export class DataService {
  constructor(private http: HttpClient) {}
private  apiUrl = 'http://localhost:8090/api';

  getMatieres(): Observable<Matiere[]> {
    return this.http.get<Matiere[]>(`${this.apiUrl}/data/matieres`);
  }

  getClasses(): Observable<Classe[]> {
    return this.http.get<Classe[]>(`${this.apiUrl}/data/classes`);
  }

  getDepartements(): Observable<Departement[]> {
    return this.http.get<Departement[]>(`${this.apiUrl}/data/departements`);
  
  }




}
