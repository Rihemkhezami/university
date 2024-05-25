import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Matiere } from '../_models/Matiere';
import { Departement } from '../_models/Departement';
import { Classe } from '../_models/Classe';
import { CanMatchFn } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ClasseService {
  private baseUrl = 'http://localhost:8090/api/classes';

  constructor(private http: HttpClient) { }

  getAllClasses(): Observable<Classe[]> {
    return this.http.get<Classe[]>(`${this.baseUrl}`);
  }

  getClasseById(id: number): Observable<Classe> {
    return this.http.get<Classe>(`${this.baseUrl}/${id}`);
  }

  createClasse(classe: Classe): Observable<Classe> {
    return this.http.post<Classe>(`${this.baseUrl}`, classe);
  }

  updateClasse(id: number, classe: Classe): Observable<Classe> {
    return this.http.put<Classe>(`${this.baseUrl}/${id}`, classe);
  }

  deleteClasse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
