package br.ufrpe.tks.exceptions;

public class UsuarioNaoEncontradoException extends Exception{
	private String matricula;
	public UsuarioNaoEncontradoException(String matricula){
		super("O usuário com matricula: " + matricula + " não foi encontrado");
		this.matricula = matricula;
	}
	public String getMatricula(){
		return matricula;
	}
}
