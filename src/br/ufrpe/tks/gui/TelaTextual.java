package br.ufrpe.tks.gui;

import java.util.Scanner;

import br.ufrpe.tks.dados.IRepositorioPessoa;
import br.ufrpe.tks.dados.RepositorioPessoa;
import br.ufrpe.tks.exceptions.LoginIncorretoException;
import br.ufrpe.tks.exceptions.SenhaIncorretaException;
import br.ufrpe.tks.exceptions.UsuarioJaCadastradoException;
import br.ufrpe.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.tks.negocios.CadastroPessoa;
import br.ufrpe.tks.negocios.Servidor;
import br.ufrpe.tks.negocios.beans.Administrador;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;

public class TelaTextual {
	public static void main(String[] args) {
		
		try {
			Servidor.getInstance().cadastrarAdministrador("Admin", 'M', "98765", "14156");
			Servidor.getInstance().cadastrarFuncionario("José Rafael", "Cabo", 'M', "12345", true, "1415");
			Pessoa teste = new Funcionario("Eder Fonseca", "Major", 'M', "5644", true, "6543");
			Servidor.getInstance().cadastrar(teste);
		} catch (UsuarioJaCadastradoException e) {
			System.out.println(e.getMessage());
		}
		
		IRepositorioPessoa repositorioPessoa = RepositorioPessoa.getInstance();
		CadastroPessoa cadastroPessoa = new CadastroPessoa(repositorioPessoa);
		
		Scanner ler = new Scanner(System.in);
		
		String matricula;
		String senha;
		
		Pessoa entrou = null;
		
		System.out.println("..:: TKS ::..");
		
		do{
			System.out.println("\nEntre com seus credenciais de acesso abaixo:\n");
			System.out.println("Matricula: ");
			matricula = ler.nextLine();
			System.out.println("Senha: ");
			senha = ler.nextLine();
		
			try{
				cadastroPessoa.login(matricula, senha);
				entrou = cadastroPessoa.procurar(matricula);
			} catch(LoginIncorretoException e){
				System.out.println(e.getMessage());
			} catch(SenhaIncorretaException e){
				System.out.println(e.getMessage());
			} catch (UsuarioNaoEncontradoException e) {
				System.out.println(e.getMessage());
			}
		}while(entrou == null);
		
		System.out.println("\nBem-Vindo " + entrou.getNome());
		
		if(entrou instanceof Administrador){
			System.out.println("Voce é um administrador");
			//Opções do Administrador
		}
		if(entrou instanceof Funcionario){
			System.out.println("Voce é um funcionario");
			//Opções do Funcionario
		}

		/*// Teste de Exclusão
		try {
			Servidor.getInstance().remover("12345");
		} catch (UsuarioNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}

		// Teste de Recuperação
		try {
			System.out.println(Servidor.getInstance().procurar("12345"));
		} catch (UsuarioNaoEncontradoException e1) {
			System.out.println(e1.getMessage());
		}

		try {
			System.out.println(Servidor.getInstance().procurar("5644"));
			System.out.println(Servidor.getInstance().procurar("98765"));
		} catch (UsuarioNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}*/
	}
}
