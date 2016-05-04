package com.tks;
import com.tks.negocio.ServidorBanco;

public class Principal {

	public static void main(String[] args) {
		
		ServidorBanco.getInstance().cadastrarFuncionario("José Rafael", "Cabo", 'M', 12345, false);
		ServidorBanco.getInstance().cadastrarFuncionario("Eder Fonseca", "Major", 'M', 67890, true);
		
		System.out.println(ServidorBanco.getInstance().procurarFuncionario(12345));
		System.out.println(ServidorBanco.getInstance().procurarFuncionario(67890));
		
		ServidorBanco.getInstance().removerFuncionario(12345);
		
		if(ServidorBanco.getInstance().procurarFuncionario(12345) != null){
			System.out.println(ServidorBanco.getInstance().procurarFuncionario(12345));
		}else{
			System.out.println("Funcionario não encontrado");
		}
	}
}