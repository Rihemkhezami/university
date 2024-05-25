import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';  // Importer MatIconModule
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatCardModule} from '@angular/material/card';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatTableModule} from '@angular/material/table';
import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { EtudiantComponent } from './etudiant/etudiant.component';
import { EnseignantComponent } from './enseignant/enseignant.component';
import { MatiereComponent } from './matiere/matiere.component';
import { ClassesComponent } from './classes/classes.component';
import { AddMatiereComponent } from './add-matiere/add-matiere.component';
import { ListmatiereComponent } from './listmatiere/listmatiere.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DepartementComponent } from './departement/departement.component';
import { UsersComponent } from './users/users.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    BoardUserComponent,
    EtudiantComponent,
    EnseignantComponent,
    MatiereComponent,
    ClassesComponent,
    AddMatiereComponent,
    ListmatiereComponent,
    DashboardComponent,
    DepartementComponent,
    UsersComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,
    MatToolbarModule,
    MatCardModule,
    MatExpansionModule,
    MatTableModule
  ],
  providers: [httpInterceptorProviders, provideAnimationsAsync()],
  bootstrap: [AppComponent]
})
export class AppModule { }
