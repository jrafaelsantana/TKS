package com.tks;
import com.tks.negocio.Servidor;

public class Principal {

	public static void main(String[] args) {
		
		Servidor.getInstance().cadastrarFuncionario("José Rafael", "Cabo", 'M', 12345, false, 3);
		Servidor.getInstance().cadastrarFuncionario("Eder Fonseca", "Major", 'M', 67890, true, 4);
		
		System.out.println(Servidor.getInstance().procurarFuncionario(12345));
	}
}