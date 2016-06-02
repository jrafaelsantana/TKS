package br.ufrpe.tks.negocios.beans;

public class Pessoa {
	
	private String nome;
	private char sexo;
	private int matricula;
	private int senha;
	
	public Pessoa(String nome, char sexo, int matricula, int senha){
		this.nome = nome;
		this.sexo = sexo;
		this.matricula = matricula;
		this.senha = senha;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public char getSexo(){
		return sexo;
	}
	
	public void setSexo(char sexo){
		this.sexo = sexo;
	}
	
	public int getMatricula() {
		return matricula;
	}
	
	public void setMatricula(int matricula){
		this.matricula = matricula;
	}
	
	public void setSenha(int novaSenha){
		this.senha = novaSenha;
	}
	
	public int getSenha(){
		return this.senha;
	}
	
	public String toString(){
		return ("Nome: " + this.getNome()+ "\n" + "Sexo: " + this.getSexo() + "\n" + "Matricula: " + this.getMatricula()+ "\n");
	}

}
