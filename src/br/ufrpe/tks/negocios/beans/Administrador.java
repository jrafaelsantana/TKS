package br.ufrpe.tks.negocios.beans;

public class Administrador extends Pessoa{
	
	public Administrador(String nome, char sexo, int matricula, int senha){
		super(nome, sexo, matricula, senha);
	}
	
	public void setMatricula(int matricula){
		super.setMatricula(matricula);
	}
	
	public int getMatricula(){
		return super.getMatricula();
	}
	
	public boolean equals(Administrador a){
		boolean resultado=false;
		
		if(((Pessoa)a).getMatricula() == ((Pessoa)this).getMatricula()){
			resultado=true;
		}
		
		return resultado;
	}
}
