package br.ufrpe.tks.exceptions;

public class SenhaIncorretaException extends Exception{
	public SenhaIncorretaException(){
		super("Senha incorreta");
	}
}
