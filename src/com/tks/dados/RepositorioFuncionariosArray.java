package com.tks.dados;

import com.tks.negocio.beans.Funcionario;

public class RepositorioFuncionariosArray {
	private static RepositorioFuncionariosArray instance;
	
	private Funcionario[] funcionarios;
	private int proxima;
	
	public static RepositorioFuncionariosArray getInstance() {
        if (instance == null) {
            instance = new RepositorioFuncionariosArray(100);
        }
        return instance;
    }
	
	private RepositorioFuncionariosArray(int qtd){
		this.funcionarios = new Funcionario[qtd];
		this.proxima = 0;
	}
	
	public void cadastrarFuncionario(Funcionario funcionario){
		this.funcionarios[this.proxima] = funcionario;
		this.proxima = proxima+1;
		if(this.proxima == this.funcionarios.length){
			this.duplicaArray();
		}
	}
	
	public void duplicaArray(){
		if(this.funcionarios != null && this.funcionarios.length > 0){
			Funcionario[] arrayDuplicado = new Funcionario[this.funcionarios.length * 2];
			for(int i=0; i<=this.funcionarios.length; i++){
				arrayDuplicado[i] = funcionarios[i];
			}
			funcionarios=arrayDuplicado;
		}
	}

}
