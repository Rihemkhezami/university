import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { StorageService } from './_services/storage.service';
import { AuthService } from './_services/auth.service';
import { EventBusService } from './_shared/event-bus.service';
import { Router } from '@angular/router'; // Importer Router

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;
  email: string = '';

  eventBusSub?: Subscription;

  constructor(
    private storageService: StorageService,
    private authService: AuthService,
    private eventBusService: EventBusService,
    private router: Router // Injecter Router
  ) {}

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      if (user) { // Check if user is defined
        this.roles = user.roles || [];
        this.email = user.email || '';
        this.username = user.username || '';

        this.showAdminBoard = this.roles.includes('ADMIN');
        this.showModeratorBoard = this.roles.includes('ENSEIGNANT');
        
        if (this.roles.includes('ETUDIANT')) {
          console.log("etudiant");
        }
        if (this.roles.includes('ROLE_ENSEIGNANT')) {
          console.log("enseignant");
        }
      } else {
        console.error('User object is undefined.');
      }
    }

    this.eventBusSub = this.eventBusService.on('logout', () => {
      this.logout();
    });
  }

  logout(): void {
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);
        this.storageService.clean();

        window.location.reload();
        this.router.navigate(['/login']); // Rediriger vers la page de connexion
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
