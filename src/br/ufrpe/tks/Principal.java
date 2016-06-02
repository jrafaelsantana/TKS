package br.ufrpe.tks;

import br.ufrpe.tks.exceptions.UsuarioJaCadastradoException;
import br.ufrpe.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.tks.negocios.Servidor;
import br.ufrpe.tks.negocios.beans.Administrador;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;

public class Principal {

	public static void main(String[] args) {
		
		//Teste de Cadastro
		try {
			Servidor.getInstance().cadastrarAdministrador("Admin", 'M', 98765, 14156);
			Servidor.getInstance().cadastrarFuncionario("José Rafael", "Cabo", 'M', 12345, true, 1415);
			Pessoa teste = new Funcionario("Eder Fonseca", "Major", 'M', 5644, true, 6543);
			Servidor.getInstance().cadastrar(teste);
		} catch (UsuarioJaCadastradoException e) {
			System.out.println(e.getMessage());
		}
		
		//Teste de Exclusão
		try {
			Servidor.getInstance().remover(12345);
		} catch (UsuarioNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
		
		//Teste de Recuperação
		try {
			System.out.println(Servidor.getInstance().procurar(12345));
		} catch (UsuarioNaoEncontradoException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			System.out.println(Servidor.getInstance().procurar(5644));
			System.out.println(Servidor.getInstance().procurar(98765));
		} catch (UsuarioNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
	}

}
