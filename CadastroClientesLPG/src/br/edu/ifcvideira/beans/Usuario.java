package br.edu.ifcvideira.beans;


public class Usuario extends Pessoa {

 private String senha;
 private String email;
 
public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getSenha() {
	return senha;
}

public void setSenha(String senha) {
	this.senha = senha;
}
 
}
