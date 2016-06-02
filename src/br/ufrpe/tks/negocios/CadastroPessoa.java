package br.ufrpe.tks.negocios;

import java.util.ArrayList;

import br.ufrpe.tks.dados.IRepositorioPessoa;
import br.ufrpe.tks.exceptions.UsuarioJaCadastradoException;
import br.ufrpe.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.tks.negocios.beans.Administrador;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;

public class CadastroPessoa {
	private IRepositorioPessoa pessoas;
	
	public CadastroPessoa(IRepositorioPessoa repositorio){
		if(repositorio != null){
			this.pessoas = repositorio;
		}else{
			IllegalArgumentException rep = new IllegalArgumentException("O repositório está com erro");
			throw rep;
		}
		
	}
	
	public void cadastrarFuncionario(String nome, String cargo, char sexo, int matricula, boolean motorista, int senha) throws UsuarioJaCadastradoException{
		if(this.pessoas.procurar(matricula) == null){
			Pessoa funcionario = new Funcionario(nome, cargo, sexo, matricula, motorista, senha);
			this.pessoas.cadastrar(funcionario);
		}else{
			UsuarioJaCadastradoException usr = new UsuarioJaCadastradoException(matricula);
			throw usr;
		}
	}
	
	public void cadastrarAdministrador(String nome, char sexo, int matricula, int senha) throws UsuarioJaCadastradoException{
		if(this.pessoas.procurar(matricula) == null){
			Pessoa funcionario = new Administrador(nome, sexo, matricula, senha);
			this.pessoas.cadastrar(funcionario);
		}else{
			UsuarioJaCadastradoException usr = new UsuarioJaCadastradoException(matricula);
			throw usr;
		}
	}
	
	public void cadastrarFuncionario(Funcionario f) throws UsuarioJaCadastradoException{
		if(f != null){
			if(this.pessoas.procurar(f.getMatricula()) == null){
				this.pessoas.cadastrar(f);
			}else{
				UsuarioJaCadastradoException usr = new UsuarioJaCadastradoException(f.getMatricula());
				throw usr;
			}
		}else{
			IllegalArgumentException rep = new IllegalArgumentException("O usuário está vazio");
			throw rep;
		}
	}
	
	public void cadastrarAdministrador(Administrador a) throws UsuarioJaCadastradoException{
		if(a != null){
			if(this.pessoas.procurar(a.getMatricula()) == null){
				this.pessoas.cadastrar(a);
			}else{
				UsuarioJaCadastradoException usr = new UsuarioJaCadastradoException(a.getMatricula());
				throw usr;
			}
		}else{
			IllegalArgumentException rep = new IllegalArgumentException("O usuário está vazio");
			throw rep;
		}
	}
	
	public void remover(int matricula) throws UsuarioNaoEncontradoException{
		Pessoa pessoa = this.pessoas.procurar(matricula);
		if(pessoa != null){
			this.pessoas.remover(pessoa);
		}else{
			UsuarioNaoEncontradoException usr = new UsuarioNaoEncontradoException(matricula);
			throw usr;
		}
	}
	
	public Pessoa procurar(int matricula) throws UsuarioNaoEncontradoException{
		Pessoa procurado = null;
		procurado = this.pessoas.procurar(matricula);
		if(procurado == null){
			UsuarioNaoEncontradoException usr = new UsuarioNaoEncontradoException(matricula);
			throw usr;
		}
		return procurado;
	}
	
	public ArrayList<Pessoa> getPessoas() {
	    return this.pessoas.getUsuarios();
	}
	
}