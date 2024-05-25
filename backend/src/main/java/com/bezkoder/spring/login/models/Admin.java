package com.bezkoder.spring.login.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {

    public Admin() {}

    public Admin(String username, String email, String password) {
        super(username, email, password);
    }
}
