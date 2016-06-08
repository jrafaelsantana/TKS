package br.ufrpe.tks.dados;

import java.util.ArrayList;

import br.ufrpe.tks.negocios.beans.Pessoa;

public class RepositorioPessoa implements IRepositorioPessoa{
	
	private static RepositorioPessoa instance;
	private ArrayList<Pessoa> pessoa;
	
	public static RepositorioPessoa getInstance(){
		if (instance == null) {
            instance = new RepositorioPessoa();
        }
        return instance;
	}
	
	private RepositorioPessoa(){
		this.pessoa = new ArrayList<Pessoa>();
	}

	@Override
	public void cadastrar(Pessoa p) {
		if(p != null){
			this.pessoa.add(p);
		}
	}

	@Override
	public void remover(Pessoa p) {
		if(p != null){
			this.pessoa.remove(p);
		}
	}

	@Override
	public Pessoa procurar(String matricula) {
		Pessoa pessoaP = null;
		if(this.pessoa.size() > 0){
			for(int i = 0; i < this.pessoa.size(); i++){
				if(matricula.equals(this.pessoa.get(i).getMatricula())){
					pessoaP = this.pessoa.get(i);
				}
			}
		}
		return pessoaP;
	}

	@Override
	public ArrayList<Pessoa> getUsuarios() {
		return this.pessoa;
	}


}