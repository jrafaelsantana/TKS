package com.tks.negocio;

import com.tks.dados.RepositorioFuncionariosArray;
import com.tks.negocio.beans.Funcionario;
import java.util.Random;

public class Sorteio {
	private Funcionario[] funcionario;
	private int qtdCotas = 120;
	private Funcionario[][] tabela = new Funcionario[120][2];
	Random gerador = new Random();
	
	public void realizaSorteio(){
		this.funcionario = RepositorioFuncionariosArray.getInstance().getArray();
		if(RepositorioFuncionariosArray.getInstance().getArray() == null){
			//Erro se for nulo
		}else{
			for(int i=1; i<=120; i++){
				int temp;
				do{
					temp = gerador.nextInt(funcionario.length);
					System.out.println(temp);
				}while(funcionario[temp].isMotorista() == false);
				tabela[i][1] = funcionario[temp];
			}
			
			for(int i=1; i<=120; i++){
				int temp;
				do{
					temp = gerador.nextInt(funcionario.length);
					while(funcionario[temp].getSexo() == 'F' && tabela[i][1].getSexo() == 'F' ){
						temp = gerador.nextInt(funcionario.length);
					}
				}while(funcionario[temp].isMotorista() == true );
				tabela[i][2] = funcionario[temp];
			}
		}
	}
	
	public Funcionario[][] getArray(){
		return tabela;
	}
}