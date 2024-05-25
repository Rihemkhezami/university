package com.bezkoder.spring.login.payload.response;

import java.util.List;

public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private List<String> roles;

	// Champs spécifiques
	private String matiere;
	private String classe;
	private String departement;

	public UserInfoResponse(Long id, String username, String email, List<String> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	// Constructeur pour inclure les champs supplémentaires
	public UserInfoResponse(Long id, String username, String email, List<String> roles, String matiere, String classe, String departement) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.matiere = matiere;
		this.classe = classe;
		this.departement = departement;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}


}
