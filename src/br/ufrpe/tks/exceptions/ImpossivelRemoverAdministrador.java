package br.ufrpe.tks.exceptions;

public class ImpossivelRemoverAdministrador extends Exception{
	public ImpossivelRemoverAdministrador(){
		super("Impossivel remover o último administrador.");
	}
}
