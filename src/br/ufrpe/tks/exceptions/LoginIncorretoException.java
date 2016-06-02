package br.ufrpe.tks.exceptions;

public class LoginIncorretoException extends Exception{
	public LoginIncorretoException(){
		super("Número da matrícula ou senha incorretos");
	}
}
