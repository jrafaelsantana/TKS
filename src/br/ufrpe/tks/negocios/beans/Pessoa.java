package br.ufrpe.tks.negocios.beans;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pessoa {

	private String nome;
	private char sexo;
	private String matricula;
	private String senha;

	public Pessoa(String nome, char sexo, String matricula, String senha) {
		this.nome = nome;
		this.sexo = sexo;
		this.matricula = matricula;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setSenha(String novaSenha) {
		this.senha = novaSenha;
	}

	public String getSenha() {
		return this.senha;
	}

	public String toString() {
		return ("Nome: " + this.getNome() + "\n" + "Sexo: " + this.getSexo() + "\n" + "Matricula: "
				+ this.getMatricula() + "\n");
	}

	public StringProperty matriculaProperty() {
		StringProperty matricula = new SimpleStringProperty(this.matricula);
		return matricula;
	}

	public StringProperty nomeProperty() {
		StringProperty nome = new SimpleStringProperty(this.nome);
		return nome;
	}

}
