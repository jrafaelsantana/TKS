package com.tks.negocio.beans;


public class Funcionario {
	private String nome;
	private String cargo;
	private char sexo;
	private int matricula;
	private boolean motorista;
	//private int plantaoDia; //Será informado na hora do seleção
	//private int qtdCotas; //Será informado na hora da seleção
	
	public Funcionario(String nome, String cargo, char sexo, int matricula, boolean motorista){
		this.nome=nome;
		this.cargo=cargo;
		this.sexo=sexo;
		this.matricula=matricula;
		this.motorista=motorista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public boolean isMotorista() {
		return motorista;
	}

	public void setMotorista(boolean motorista) {
		this.motorista = motorista;
	}
	
	public boolean equals(Funcionario f){
		boolean resultado=false;
		
		if(f.matricula == this.matricula){
			resultado=true;
		}
		
		return resultado;
	}
	
	public String toString(){
		return "Cargo: " + this.cargo + "\n" +
				"Nome: " + this.nome + "\n" +
				"Matricula: " + this.matricula + "\n" +
				"Sexo: " + this.sexo + "\n" +
				"Motorista: " + this.motorista + "\n";
	}
}