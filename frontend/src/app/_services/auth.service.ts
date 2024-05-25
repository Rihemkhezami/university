// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Matiere } from '../_models/Matiere';
import { Classe } from '../_models/Classe';
import { Departement } from '../_models/Departement';

const AUTH_API = 'http://localhost:8090/api/auth/';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private apiUrl = 'http://localhost:8090/api';

  constructor(private http: HttpClient) {}
  getMatieres(): Observable<Matiere[]> {
    return this.http.get<Matiere[]>(`${this.apiUrl}/data/matieres`);
  }

  getClasses(): Observable<Classe[]> {
    return this.http.get<Classe[]>(`${this.apiUrl}/data/classes`);
  }

  getDepartements(): Observable<Departement[]> {
    return this.http.get<Departement[]>(`${this.apiUrl}/data/departements`);
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signin',
      {
        username,
        password,
      },
      { observe: 'response' }
    );
  }

  register(username: string, email: string, password: string, role: string, matiere: string, classe: string, departement?: string): Observable<any> {
    const user = {
      username,
      email,
      password,
      role,
      matiere,
      classe,
      departement,
    };
    return this.http.post(AUTH_API + 'signup', user);
  }

  logout(): Observable<any> {
    return this.http.post(AUTH_API + 'signout', {});
  }

  saveToken(token: string): void {
    localStorage.setItem('auth-token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('auth-token');
  }
}
