package br.ufrpe.tks.exceptions;

public class UsuarioJaCadastradoException extends Exception{
	private String matricula;
	public UsuarioJaCadastradoException(String matricula){
		super("O usuário com matrícula: " + matricula + " já está cadastrado.");
		this.matricula = matricula;
	}
	public String getMatricula(){
		return matricula;
	}
}
