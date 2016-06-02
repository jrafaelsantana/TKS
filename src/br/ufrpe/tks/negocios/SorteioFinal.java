package br.ufrpe.tks.negocios;

import java.util.Random;

import br.ufrpe.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;
import br.ufrpe.tks.negocios.beans.Selecionado;

public class SorteioFinal {
	
	private EscalaMes escalados;
	
	public SorteioFinal(EscalaMes escalados){
		this.escalados = escalados;
	}
	
	private void auxSorteioIDEscala(int verificador, Selecionado [] selecionadoTemp, int i, int j){
		Random num = new Random();
		int N = 0;
		switch(verificador){
		
		case 1: {
			do{
				int gerador = num.nextInt(29);
				gerador += 1;
				selecionadoTemp[i].setDiasSorteados(j, gerador);
				N = selecionadoTemp[i].getDiasSorteados(j);
			}while( N != 3 && N != 7 && N != 11 && N != 15 && N != 19 && N != 23 && N != 27);
				break;
		}
		
		case 2: {
			do{
				int gerador = num.nextInt(29);
				gerador += 1;
				selecionadoTemp[i].setDiasSorteados(j, gerador);
				N = selecionadoTemp[i].getDiasSorteados(j);
			}while( N != 4 && N != 8 && N != 12 && N != 16 && N != 20 && N != 24 && N != 28);
				break;
		}
		
		case 3: {
			do{
				int gerador = num.nextInt(29);
				gerador += 1;
				selecionadoTemp[i].setDiasSorteados(j, gerador);
				N = selecionadoTemp[i].getDiasSorteados(j);
			}while( N != 1 && N != 5 && N != 9 && N != 13 && N != 17 && N != 21 && N != 25 && N != 29);
				break;
		}
		
		case 4: {
			do{
				int gerador = num.nextInt(29);
				gerador += 1;
				selecionadoTemp[i].setDiasSorteados(j, gerador);
				N = selecionadoTemp[i].getDiasSorteados(j);
			}while( N != 2 && N != 6 && N != 10 && N != 14 && N != 18 && N != 22 && N != 26 && N != 30);
				break;
		}
		
		}
		
	}
	
	
	
//BUSCAR EM CADA ARRAY SE HÁ DIAS REPETIDOS, NAO PODE TRABALHAR 2X MESMO DIA.
	private void arrayIndividual(int a, Selecionado [] temp){
		int [] valor = new int [temp[a].getQtdExtras()];
		int count;
		for(count = 0; count < temp[a].getQtdExtras(); count++){
				valor[count] = temp[a].getDiasSorteados(count);
		}//FIZ UMA COPIA DOS INTEIROS DO ARRAY INDIVIDUAL.
		for(count = 0; count < temp[a].getQtdExtras(); count++){
			for(int valor1 = 1; valor1 < temp[a].getQtdExtras(); valor1++){
				if(valor[count] != 0 && valor[count] == temp[a].getDiasSorteados(valor1)){
					temp[a].setDiasSorteados(valor1, 0);
				}
			}
		}//COMPARANDO TODAS AS POSIÇOES DA COPIA COM O ORIGINAL A VER SE HA DIAS REPETIDOS.
	}//AO FINAL SE UM DIA FOR REPETIDO RECEBE VALOR ZERO PRA SER NOVAMENTE SORTEADO.
	
	public void realizarSorteio(EscalaMes x) throws UsuarioNaoEncontradoException{
		
		Pessoa [] pessoaTemp = new Pessoa[x.getQtdEscolhidos()];//array de pessoas
		int [] matricula = new int [x.getQtdEscolhidos()];//array de matriculas
		Selecionado [] selecionadoTemp = new Selecionado [x.getQtdEscolhidos()];//array de selecionados
		for(int count = 0; count < x.getQtdEscolhidos(); count++){//CARREGUEI OS ESCOLHIDOS NUM ARRAY DE SELECIONADO.
			selecionadoTemp[count] = x.getEscolhidos(count);
		}
		for(int i = 0; i < x.getQtdEscolhidos(); i++){//CARREGUEI AS MATRICULAS NUM ARRAY INTEIROS.
			matricula[i] = selecionadoTemp[i].getMatricula();
		}
		for(int i = 0; i < x.getQtdEscolhidos(); i++){//CARREGUEI OS ADM. E FUNC. NUM ARRAY DE PESSOAS.
			pessoaTemp[i] = Servidor.getInstance().procurar(matricula[i]);
		}//AGORA TENHO A INFORMAÇAO COMPLETA DE TODOS OS FUNCIONARIOS E ADM. EM PESSOATEMP.
		for(int i = 0; i < selecionadoTemp.length; i++){
			for(int j = 0; j < selecionadoTemp[i].getQtdExtras(); j++){
				if(selecionadoTemp[i].getDiasSorteados(j) == 0){
						if(pessoaTemp[i] instanceof Funcionario){
							Funcionario funcTemp = null;
							funcTemp = (Funcionario) pessoaTemp[i];
							if(funcTemp.isMotorista()){
								int verificador = selecionadoTemp[i].getEscala();//CAPTURANDO A ESCALA DO INDIVIDUO.
								this.auxSorteioIDEscala(verificador, selecionadoTemp, verificador, j);
							}
						}
					//verificar todos os arrays da EscalaMes pra ver se tem mais de 2 dias repetidos
				}
			}//AQUI TERMINA O FOR 'J' QUE SORTEIA TODOS OS DIAS LIVRES DE CADA MOTORISTA. 
			this.arrayIndividual(i, selecionadoTemp);//VERIFICAR LOGICA AINDA
			}//AQUI TERMINA O FOR 'I' QUE PASSA TODOS OS FUNCIONARIOS.
	}
}