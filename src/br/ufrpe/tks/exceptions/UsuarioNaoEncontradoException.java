package br.ufrpe.tks.exceptions;

public class UsuarioNaoEncontradoException extends Exception{
	private int matricula;
	public UsuarioNaoEncontradoException(int matricula){
		super("O usuário com matricula: " + matricula + " não foi encontrado");
		this.matricula = matricula;
	}
	public int getMatricula(){
		return matricula;
	}
}
