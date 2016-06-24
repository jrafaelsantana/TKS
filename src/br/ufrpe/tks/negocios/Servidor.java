package br.ufrpe.tks.negocios;

import java.util.ArrayList;

import br.ufrpe.tks.dados.RepositorioPessoa;
import br.ufrpe.tks.exceptions.UsuarioJaCadastradoException;
import br.ufrpe.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.tks.negocios.beans.Administrador;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;

public class Servidor {
	private static Servidor instance;
	private CadastroPessoa pessoa;
	
	private Servidor(){
		this.pessoa = new CadastroPessoa(RepositorioPessoa.getInstance());
	}
	
	public static Servidor getInstance() {
        if (instance == null) {
            instance = new Servidor();
        }
        return instance;
    }
	
	public void cadastrar(Pessoa p) throws UsuarioJaCadastradoException{
		if(p instanceof Administrador){
			pessoa.cadastrarAdministrador((Administrador) p);
		}else if(p instanceof Funcionario){
			pessoa.cadastrarFuncionario((Funcionario) p);
		}
		Servidor.getInstance().salvardb();
	}
	
	public void cadastrarFuncionario(String nome, String cargo, char sexo, String matricula, boolean motorista, String senha) throws UsuarioJaCadastradoException{
		pessoa.cadastrarFuncionario(nome, cargo, sexo, matricula, motorista, senha);
		Servidor.getInstance().salvardb();
	}
	
	public void cadastrarAdministrador(String nome, char sexo, String matricula, String senha) throws UsuarioJaCadastradoException{
		pessoa.cadastrarAdministrador(nome, sexo, matricula, senha);
		Servidor.getInstance().salvardb();
	}
	
	public Pessoa procurar(String matricula) throws UsuarioNaoEncontradoException{
		return pessoa.procurar(matricula);
	}
	
	public void remover(String matricula) throws UsuarioNaoEncontradoException{
		pessoa.remover(matricula);
		Servidor.getInstance().salvardb();
	}
	
	public ArrayList<Pessoa> getPessoas() {
	    return this.pessoa.getPessoas();
	}
	
	public void salvardb(){
		RepositorioPessoa.getInstance().salvarbd();
	}

}