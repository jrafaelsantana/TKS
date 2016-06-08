package br.ufrpe.tks.negocios;

import java.util.ArrayList;

import br.ufrpe.tks.dados.IRepositorioPessoa;
import br.ufrpe.tks.exceptions.LoginIncorretoException;
import br.ufrpe.tks.exceptions.SenhaIncorretaException;
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
	
	public void cadastrarFuncionario(String nome, String cargo, char sexo, String matricula, boolean motorista, String senha) throws UsuarioJaCadastradoException{
		if(this.pessoas.procurar(matricula) == null){
			Pessoa funcionario = new Funcionario(nome, cargo, sexo, matricula, motorista, senha);
			this.pessoas.cadastrar(funcionario);
		}else{
			UsuarioJaCadastradoException usr = new UsuarioJaCadastradoException(matricula);
			throw usr;
		}
	}
	
	public void cadastrarAdministrador(String nome, char sexo, String matricula, String senha) throws UsuarioJaCadastradoException{
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
	
	public void remover(String matricula) throws UsuarioNaoEncontradoException{
		Pessoa pessoa = this.pessoas.procurar(matricula);
		if(pessoa != null){
			this.pessoas.remover(pessoa);
		}else{
			UsuarioNaoEncontradoException usr = new UsuarioNaoEncontradoException(matricula);
			throw usr;
		}
	}
	
	public Pessoa procurar(String matricula) throws UsuarioNaoEncontradoException{
		Pessoa procurado = null;
		procurado = this.pessoas.procurar(matricula);
		if(procurado == null){
			UsuarioNaoEncontradoException usr = new UsuarioNaoEncontradoException(matricula);
			throw usr;
		}
		return procurado;
	}
	
	public boolean login(String matricula, String senha) throws LoginIncorretoException, SenhaIncorretaException{
		boolean efetuado = false;
		if(matricula != null && senha != null){
			Pessoa auxiliar = this.pessoas.procurar(matricula);
			if(auxiliar != null){
				if(auxiliar.getSenha().equals(senha)){
					efetuado = true;
				}else{
					SenhaIncorretaException x = new SenhaIncorretaException();
					throw x;
				}
			}else{
				LoginIncorretoException x = new LoginIncorretoException();
				throw x;
			}
		}
		return efetuado;
	}
	
	public ArrayList<Pessoa> getPessoas() {
	    return this.pessoas.getUsuarios();
	}
	
}