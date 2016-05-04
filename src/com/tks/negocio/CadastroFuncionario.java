package com.tks.negocio;
import com.tks.dados.RepositorioFuncionariosArray;
import com.tks.negocio.beans.Funcionario;

public class CadastroFuncionario {
	private RepositorioFuncionariosArray repostorio;
	
	public CadastroFuncionario(){
		this.repostorio = new RepositorioFuncionariosArray(100);
	}
	
	public void cadastrar(Funcionario f){
		if(f == null){
			//Se funcionario for nulo
		}else{
			if(!this.existe(f.getMatricula())){
				this.repostorio.cadastrarFuncionario(f);
			}
		}
	}
	
	public void apagar(int matricula){
		Funcionario f = this.repostorio.procurar(matricula);
		if(f != null){
			this.repostorio.removerFuncionario(matricula);
		}else{
			//Erro
		}
	}
	
	public boolean existe(int matricula){
		return this.repostorio.existe(matricula);
	}
	
	public Funcionario procurar(int matricula) {
        return this.repostorio.procurar(matricula);
    }

}