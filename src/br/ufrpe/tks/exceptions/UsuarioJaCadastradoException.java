package br.ufrpe.tks.exceptions;

public class UsuarioJaCadastradoException extends Exception{
	private int matricula;
	public UsuarioJaCadastradoException(int matricula){
		super("O usuário com matrícula: " + matricula + " já está cadastrado.");
		this.matricula = matricula;
	}
	public int getMatricula(){
		return matricula;
	}
}
