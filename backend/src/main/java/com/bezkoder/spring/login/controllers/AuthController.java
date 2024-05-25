package com.bezkoder.spring.login.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.login.models.ERole;
import com.bezkoder.spring.login.models.Role;
import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.models.Admin;
import com.bezkoder.spring.login.models.Enseignant;
import com.bezkoder.spring.login.models.Etudiant;
import com.bezkoder.spring.login.models.Matiere;
import com.bezkoder.spring.login.models.Classe;
import com.bezkoder.spring.login.models.Departement;
import com.bezkoder.spring.login.payload.request.LoginRequest;
import com.bezkoder.spring.login.payload.request.SignupRequest;
import com.bezkoder.spring.login.payload.response.UserInfoResponse;
import com.bezkoder.spring.login.payload.response.MessageResponse;
import com.bezkoder.spring.login.repository.RoleRepository;
import com.bezkoder.spring.login.repository.UserRepository;
import com.bezkoder.spring.login.repository.MatiereRepository;
import com.bezkoder.spring.login.repository.ClasseRepository;
import com.bezkoder.spring.login.repository.DepartementRepository;
import com.bezkoder.spring.login.security.jwt.JwtUtils;
import com.bezkoder.spring.login.security.services.UserDetailsImpl;

@CrossOrigin(origins = "http://localhost:4200/",maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  MatiereRepository matiereRepository;

  @Autowired
  ClasseRepository classeRepository;

  @Autowired
  DepartementRepository departementRepository;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

    User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

    String matiere = null;
    String classe = null;
    String departement = null;

    if (user instanceof Enseignant) {
      Enseignant enseignantUser = (Enseignant) user;
      matiere = enseignantUser.getMatiere().getNom();
      classe = enseignantUser.getClasse().getNom();
      departement = enseignantUser.getDepartement().getNom();
    } else if (user instanceof Etudiant) {
      Etudiant etudiantUser = (Etudiant) user;
      matiere = etudiantUser.getMatiere().getNom();
      classe = etudiantUser.getClasse().getNom();
    }

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles, matiere, classe, departement));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    User user;
    Set<Role> roles = new HashSet<>();

    if (signUpRequest.getEmail().contains("@admin")) {
      Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(adminRole);
      user = new Admin(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
    } else if (signUpRequest.getEmail().contains("@enseignant")) {
      Role enseignantRole = roleRepository.findByName(ERole.ROLE_ENSEIGNANT).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(enseignantRole);
      Matiere matiere = matiereRepository.findByNom(signUpRequest.getMatiere()).orElseThrow(() -> new RuntimeException("Error: Matiere is not found."));
      Classe classe = classeRepository.findByNom(signUpRequest.getClasse()).orElseThrow(() -> new RuntimeException("Error: Classe is not found."));
      Departement departement = departementRepository.findByNom(signUpRequest.getDepartement()).orElseThrow(() -> new RuntimeException("Error: Departement is not found."));
      user = new Enseignant(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), matiere, classe, departement);
    } else {
      Role etudiantRole = roleRepository.findByName(ERole.ROLE_ETUDIANT).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(etudiantRole);
      Matiere matiere = matiereRepository.findByNom(signUpRequest.getMatiere()).orElseThrow(() -> new RuntimeException("Error: Matiere is not found."));
      Classe classe = classeRepository.findByNom(signUpRequest.getClasse()).orElseThrow(() -> new RuntimeException("Error: Classe is not found."));
      //Enseignant enseignant = userRepository.findByUsername(signUpRequest.getEnseignant()).filter(u -> u instanceof Enseignant).map(u -> (Enseignant) u).orElseThrow(() -> new RuntimeException("Enseignant not found"));
      user = new Etudiant(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), matiere, classe);
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new MessageResponse("You've been signed out!"));
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userRepository.findAll();
    return ResponseEntity.ok(users);
  }




}
