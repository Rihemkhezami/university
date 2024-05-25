import { Component, OnInit } from '@angular/core';
import { User } from '../_models/User';
import { UsersService } from '../_services/users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'] // Correction de la propriété styleUrls
})
export class UsersComponent implements OnInit {
  users: User[] = [];
  isUpdating: boolean = false;

  constructor(private usersService: UsersService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.usersService.getAllUsers().subscribe(
      users => {
        this.users = users;
      },
      error => {
        console.log('Error fetching users:', error);
      }
    );
  }
}
