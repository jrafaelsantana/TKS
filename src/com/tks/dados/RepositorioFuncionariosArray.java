package com.tks.dados;

import com.tks.negocio.beans.Funcionario;

public class RepositorioFuncionariosArray {
	private static RepositorioFuncionariosArray instance;
	
	private Funcionario[] funcionarios;
	private int proxima;
	
	public static RepositorioFuncionariosArray getInstance() {
        if (instance == null) {
            instance = new RepositorioFuncionariosArray(1);
        }
        return instance;
    }
	
	public RepositorioFuncionariosArray(int qtd){
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
	
	public void cadastrarFuncionario(String nome, String cargo, char sexo, int matricula, boolean motorista, int qtdCotas){
		Funcionario temp = new Funcionario(nome,cargo,sexo,matricula,motorista,qtdCotas);
		this.funcionarios[this.proxima] = temp;
		this.proxima = proxima+1;
		if(this.proxima == this.funcionarios.length){
			this.duplicaArray();
		}
	}
	
	public void removerFuncionario(int matricula) {
		int i = this.pesquisaIndice(matricula);
		if (i != this.proxima) {
			this.funcionarios[i] = this.funcionarios[this.proxima - 1];
			this.funcionarios[this.proxima - 1] = null;
	        this.proxima = this.proxima - 1;
		}
	}
	
	public boolean existe(int matricula) {
		boolean existe = false;
		int indice = this.pesquisaIndice(matricula);
		if (indice != proxima) {
			existe = true;
        }
        return existe;
    }
	
	public void duplicaArray(){
		if(this.funcionarios != null && this.funcionarios.length > 0){
			Funcionario[] arrayDuplicado = new Funcionario[this.funcionarios.length + 1];
			for(int i=0; i<=this.funcionarios.length; i++){
				arrayDuplicado[i] = funcionarios[i];
			}
			funcionarios=arrayDuplicado;
		}
	}
	
	private int pesquisaIndice(int matricula){
		int resultado = 0;
		boolean achouIndice = false;
		while ((!achouIndice) && (resultado < this.proxima)) {
            if (matricula == this.funcionarios[resultado].getMatricula()) {
            	achouIndice = true;
            } else {
                resultado = resultado + 1;
            }
        }
		return resultado;
	}
	
	public Funcionario procurar(int matricula){
		int i = this.pesquisaIndice(matricula);
		Funcionario funcionario = null;
		if(i != this.proxima){
			funcionario = this.funcionarios[i];
		}
		return funcionario;
	}
	
	public Funcionario[] getArray(){
		return funcionarios;
	}
}
