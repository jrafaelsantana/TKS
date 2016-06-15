package br.ufrpe.tks.negocios;

import java.time.LocalDate;
import java.util.Scanner;

import br.ufrpe.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;
import br.ufrpe.tks.negocios.beans.Selecionado;

public class EscalaMes {
	private LocalDate  mesAno;
	private Selecionado [] escolhidos;
	private int qtdEscolhidos;
	
	public EscalaMes(LocalDate mesAno, int qtdEscolhidos){
		this.escolhidos = new Selecionado [qtdEscolhidos];
		this.mesAno = mesAno;
		this.qtdEscolhidos = qtdEscolhidos;
	}
	//OK
	private boolean validarArray(Selecionado [] escolhidos) throws UsuarioNaoEncontradoException{
		int count = 0;
		boolean resultado = true;
		Selecionado temp;
		for(int i = 0; i < escolhidos.length; i ++){
			temp = escolhidos[i];
			count = count + temp.getQtdExtras();

			if(i == escolhidos.length - 1 && count < 120){//CONTANDO SERVIÇOS TOTAIS.
				System.out.println("ERRO!!! EXITEM MENOS DE 120 SERVICOS");
				resultado = false;
			}
			if(i == escolhidos.length - 1){
				int extrasMotorista = 0;
				int contaMulheres = 0;
				for(int seilah = 0; seilah < escolhidos.length; seilah++){
					
					String m = escolhidos[seilah].getMatricula();
					Pessoa teste = Servidor.getInstance().procurar(m);
					
					if(teste.getSexo() == 'F'){
						contaMulheres += escolhidos[seilah].getQtdExtras();
					}
					
					if(teste instanceof Funcionario){
						Funcionario temp2 = (Funcionario) teste; 
						if(temp2.isMotorista()){
							extrasMotorista += escolhidos[seilah].getQtdExtras();
						}
					}
				}
				if(extrasMotorista < 60){//CONTANDO SERVIÇOS DE MOTORISTA.
					System.out.println("ERRO! EXISTEM MENOS DE 60 SERVIÇOS DE MOTORISTA");
					resultado = false;
				}
				if(contaMulheres > 60){
					System.out.print("ERRO! EXISTEM MAIS DE 60 SERVIÇOS DE MULHERES");
					resultado = false;
				}
					
			}
		}
		return resultado;
	}
	
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
	
	//OK
	private void contarMotEscala(Selecionado [] x) throws UsuarioNaoEncontradoException{//contar motoristas por escalas. 
		int esc1 = 0, esc2 = 0, esc3 = 0, esc4 = 0, esc0 = 0;
		Funcionario temp = null;
		for(int i = 0; i < x.length; i++){
				temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());

				if( temp.isMotorista() ){
					switch(x[i].getEscala()){
					case 1: {
						esc1 += x[i].getQtdExtras();
						break;
					}
					case 2: {
						esc2 += x[i].getQtdExtras();
						break;
					}
					case 3: {
						esc3 += x[i].getQtdExtras();
						break;
					}
					case 4: {
						esc4 += x[i].getQtdExtras();
						break;
					}
					case 0: {
						esc0 += x[i].getQtdExtras();
						break;
					}
					}
				}
				
			}

		do{
			if(esc0 != 0){
				if(esc1 < 14){System.out.println("IF ESC 1");
					for(int i = 0; i < this.qtdEscolhidos; i++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
						if(x[i].getEscala() == 0 & temp.isMotorista()){
							x[i].setEscala(1);
							esc0 = esc0 - x[i].getQtdExtras();
							esc1 = esc1 + x[i].getQtdExtras();
							break;
						}
					}
				}
				if(esc2 < 14){System.out.println("IF ESC 2");
					for(int i = 0; i < this.qtdEscolhidos; i++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
						if(x[i].getEscala() == 0 & temp.isMotorista()){
							x[i].setEscala(2);
							esc0 -= x[i].getQtdExtras();
							esc2 += x[i].getQtdExtras();
							break;
						}
					}
				}
				if(esc3 < 16){System.out.println("IF ESC 3");
					for(int i = 0; i < this.qtdEscolhidos; i++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
						if(x[i].getEscala() == 0 & temp.isMotorista()){
							x[i].setEscala(3);
							esc0 -= x[i].getQtdExtras();
							esc3 += x[i].getQtdExtras();
							break;
						}
					}
				}
				if(esc4 < 16){System.out.println("IF ESC 4");
					for(int i = 0; i < this.qtdEscolhidos; i++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
						if(x[i].getEscala() == 0 & temp.isMotorista()){
							x[i].setEscala(4);
							esc0 -= x[i].getQtdExtras();
							esc4 += x[i].getQtdExtras();
							break;
						}
					}
				}
				System.out.println(esc0);
				if(esc1 >= 14 & esc2 >= 14 & esc3 >= 16 & esc4 >= 16){
					esc0 = 0;
					System.out.println("IF ESC 0 recebendo 0");
				}
				System.out.println(esc1 + " -1- " + esc2 + " -2- " + esc3 + " -3- " + esc4 + " -4- " + esc0 + " -0-");
			}
		}while(esc0 != 0);
		System.out.println("Cheguei ao final");
		//OK AKI -------------------------------------------------------------------------------
		
		//COMEÇO DE DISTRIBUIÇAO DE MOTORISTAS DE ESCALA FIXA PARA ESCALAS CONGRUENTES.
		
		int var = 0;//quantidade de dias que ainda faltam ser sorteados.
		if(esc1 < 14){//se falta mot em esc1
			int dif1 = 0;
			dif1 = 14 - esc1;//dif1 é o que falta em esc1
			if(esc2 > 14){//se sobra mot em esc2
				int dif2 = 0;
				dif2 = esc2 - 14;//dif2 é o que sobra em esc2
				do{
				if(dif2 >= dif1){//se o que sobra é maior ou igual ao que falta... transfere.
					for(int count = 0; count < x.length; count++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[count].getMatricula());
						if(temp.isMotorista() == true){
							if(temp.getEscala() == 2){
								if(!temp.isTransferido()){
									for(int count2 = 0; count2 < x[count].getQtdExtras(); count2++ ){
										if(x[count].getDiasSorteados(count2) == 0){
											var++;
										}
									}
									if(var <= dif1 || var > dif1 & dif2 - var >= 0){
										temp.setTransferido(true);
										x[count].setEscala(1);
										esc1 = esc1 + var;
										esc2 = esc2 - var;
										dif1 = dif1 - var;
										dif2 = dif2 - var;
										var = 0;
									}
									else{
										var = 0;
									}
										
								}
							}
						}
					}
				}
			}while(esc1 < 14 || esc2 > 14);
			}
			//-------------------------------------------------------------------------
			if(esc4 > 16){//se sobra mot em esc4
				int dif4 = 0;
				dif4 = esc4 - 16;//dif4 é o que sobra em esc4
				do{
				if(dif4 >= dif1){//se o que sobra é maior ou igual ao que falta... transfere.
					for(int count = 0; count < x.length; count++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[count].getMatricula());
						if(temp.isMotorista() == true){
							if(temp.getEscala() == 4){
								if(!temp.isTransferido()){
									for(int count2 = 0; count2 < x[count].getQtdExtras(); count2++ ){
										if(x[count].getDiasSorteados(count2) == 0){
											var++;
										}
									}
									if(var <= dif1 || var > dif1 & dif4 - var >= 0){
										temp.setTransferido(true);
										x[count].setEscala(1);
										esc1 = esc1 + var;
										esc4 = esc4 - var;
										dif1 = dif1 - var;
										dif4 = dif4 - var;
										var = 0;
									}
									else{
										var = 0;
									}
										
								}
							}
						}
					}
				}
			}while(esc1 < 14 || esc4 > 16);
			}
		}
		//final de esc1--- inicio de esc3------------------------------------------------------------------------------------
		
		if(esc3 < 16){//se falta mot em esc1
			int dif3 = 0;
			dif3 = 14 - esc3;//dif1 é o que falta em esc1
			if(esc2 > 14){//se sobra mot em esc2
				int dif2 = 0;
				dif2 = esc2 - 14;//dif2 é o que sobra em esc2
				do{
				if(dif2 >= dif3){//se o que sobra é maior ou igual ao que falta... transfere.
					for(int count = 0; count < x.length; count++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[count].getMatricula());
						if(temp.isMotorista() == true){
							if(temp.getEscala() == 2){
								if(!temp.isTransferido()){
									for(int count2 = 0; count2 < x[count].getQtdExtras(); count2++ ){
										if(x[count].getDiasSorteados(count2) == 0){
											var++;
										}
									}
									if(var <= dif3 || var > dif3 & dif2 - var >= 0){
										temp.setTransferido(true);
										x[count].setEscala(3);
										esc3 = esc3 + var;
										esc2 = esc2 - var;
										dif3 = dif3 - var;
										dif2 = dif2 - var;
										var = 0;
									}
									else{
										var = 0;
									}
										
								}
							}
						}
					}
				}
			}while(esc3 < 16 || esc2 > 14);
			}
			//-------------------------------------------------------------------------
			if(esc4 > 16){//se sobra mot em esc4
				int dif4 = 0;
				dif4 = esc4 - 16;//dif4 é o que sobra em esc4
				do{
				if(dif4 >= dif3){//se o que sobra é maior ou igual ao que falta... transfere.
					for(int count = 0; count < x.length; count++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[count].getMatricula());
						if(temp.isMotorista() == true){
							if(temp.getEscala() == 4){
								if(!temp.isTransferido()){
									for(int count2 = 0; count2 < x[count].getQtdExtras(); count2++ ){
										if(x[count].getDiasSorteados(count2) == 0){
											var++;
										}
									}
									if(var <= dif3 || var > dif3 & dif4 - var >= 0){
										temp.setTransferido(true);
										x[count].setEscala(3);
										esc3 = esc3 + var;
										esc4 = esc4 - var;
										dif3 = dif3 - var;
										dif4 = dif4 - var;
										var = 0;
									}
									else{
										var = 0;
									}
										
								}
							}
						}
					}
				}
			}while(esc3 < 16 || esc4 > 16);
			}
		}
		//final de esc3--- inicio de esc2------------------------------------------------------------
		
		if(esc2 < 14){//se falta mot em esc2
			int dif2 = 0;
			dif2 = 14 - esc2;//dif2 é o que falta em esc2
			if(esc1 > 14){//se sobra mot em esc1
				int dif1 = 0;
				dif1 = esc1 - 14;//dif1 é o que sobra em esc1
				do{
				if(dif1 >= dif2){//se o que sobra é maior ou igual ao que falta... transfere.
					for(int count = 0; count < x.length; count++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[count].getMatricula());
						if(temp.isMotorista() == true){
							if(temp.getEscala() == 1){
								if(!temp.isTransferido()){
									for(int count2 = 0; count2 < x[count].getQtdExtras(); count2++ ){
										if(x[count].getDiasSorteados(count2) == 0){
											var++;
										}
									}
									if(var <= dif2 || var > dif2 & dif1 - var >= 0){
										temp.setTransferido(true);
										x[count].setEscala(2);
										esc2 = esc2 + var;
										esc1 = esc1 - var;
										dif2 = dif2 - var;
										dif1 = dif1 - var;
										var = 0;
									}
									else{
										var = 0;
									}
										
								}
							}
						}
					}
				}
			}while(esc2 < 14 || esc1 > 14);
			}
			//-------------------------------------------------------------------------
			if(esc3 > 16){//se sobra mot em esc3
				int dif3 = 0;
				dif3 = esc3 - 16;//dif3 é o que sobra em esc3
				do{
				if(dif3 >= dif2){//se o que sobra é maior ou igual ao que falta... transfere.
					for(int count = 0; count < x.length; count++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[count].getMatricula());
						if(temp.isMotorista() == true){
							if(temp.getEscala() == 3){
								if(!temp.isTransferido()){
									for(int count2 = 0; count2 < x[count].getQtdExtras(); count2++ ){
										if(x[count].getDiasSorteados(count2) == 0){
											var++;
										}
									}
									if(var <= dif2 || var > dif2 & dif3 - var >= 0){
										temp.setTransferido(true);
										x[count].setEscala(2);
										esc2 = esc2 + var;
										esc3 = esc3 - var;
										dif2 = dif2 - var;
										dif3 = dif3 - var;
										var = 0;
									}
									else{
										var = 0;
									}
										
								}
							}
						}
					}
				}
			}while(esc2 < 14 || esc3 > 16);
			}
		}
		//final de esc2--- inicio de esc4------------------------------------------------------------------------------------

		if(esc4 < 14){//se falta mot em esc4
			int dif4 = 0;
			dif4 = 14 - esc4;//dif2 é o que falta em esc2
			if(esc1 > 14){//se sobra mot em esc1
				int dif1 = 0;
				dif1 = esc1 - 14;//dif1 é o que sobra em esc1
				do{
				if(dif1 >= dif4){//se o que sobra é maior ou igual ao que falta... transfere.
					for(int count = 0; count < x.length; count++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[count].getMatricula());
						if(temp.isMotorista() == true){
							if(temp.getEscala() == 1){
								if(!temp.isTransferido()){
									for(int count2 = 0; count2 < x[count].getQtdExtras(); count2++ ){
										if(x[count].getDiasSorteados(count2) == 0){
											var++;
										}
									}
									if(var <= dif4 || var > dif4 & dif1 - var >= 0){
										temp.setTransferido(true);
										x[count].setEscala(4);
										esc4 = esc4 + var;
										esc1 = esc1 - var;
										dif4 = dif4 - var;
										dif1 = dif1 - var;
										var = 0;
									}
									else{
										var = 0;
									}
										
								}
							}
						}
					}
				}
			}while(esc4 < 16 || esc1 > 14);
			}
			//-------------------------------------------------------------------------
			if(esc3 > 16){//se sobra mot em esc3
				int dif3 = 0;
				dif3 = esc3 - 16;//dif3 é o que sobra em esc3
				do{
				if(dif3 >= dif4){//se o que sobra é maior ou igual ao que falta... transfere.
					for(int count = 0; count < x.length; count++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[count].getMatricula());
						if(temp.isMotorista() == true){
							if(temp.getEscala() == 3){
								if(!temp.isTransferido()){
									for(int count2 = 0; count2 < x[count].getQtdExtras(); count2++ ){
										if(x[count].getDiasSorteados(count2) == 0){
											var++;
										}
									}
									if(var <= dif4 || var > dif4 & dif3 - var >= 0){
										temp.setTransferido(true);
										x[count].setEscala(4);
										esc4 = esc4 + var;
										esc3 = esc3 - var;
										dif4 = dif4 - var;
										dif3 = dif3 - var;
										var = 0;
									}
									else{
										var = 0;
									}
										
								}
							}
						}
					}
				}
			}while(esc4 < 16 || esc3 > 16);
			}
		}
		//final de esc4---------------------------------------------------------------------------------------
		
//AO FINAL DISTRIBUI A ESCALA zero PRA QQ ESCALA QUE ESTEJA FALTANDO MOTORISTA, ATE ACABAR esc0.
	}//OU ATE ACABAR COMPLETANDO TODAS AS ESCALAS COM ESCALAS FIXAS MESMO CASO A zero NAO SEJA SUFICIENTE.
	
	private void apagaDiasExcesso(Selecionado [] x) throws UsuarioNaoEncontradoException{
//APAGA 1 SERVIÇO DE MOTORISTA DO DIA Q TIVER MAIS DE 2 SERVIÇOS DE MOTORISTA
//APAGA 1 SERVIÇO DE MULHER DO DIA Q TIVER MAIS DE 2 SERVIÇOS DE MULHER.
		int contador = 0;
		int contadorF = 0;
		Funcionario temp = null;
		for(int i = 1; i <= 30; i++){
			contador = 0;
			contadorF = 0;
			for(int j = 0; j < x.length; j++){
				for(int k = 0; k < x[j].getQtdExtras(); k++){
					temp = (Funcionario) Servidor.getInstance().procurar(x[j].getMatricula());
					if(temp.isMotorista() && i == x[j].getDiasSorteados(k)){
						contador++;
						if(contador > 2){
							x[j].setDiasSorteados(k, 0);
							contador--;
						}
					}
					if(temp.getSexo() == 'F' && i == x[j].getDiasSorteados(k)){
						contadorF++;
						if(contadorF > 2){
							x[j].setDiasSorteados(k, 0);
							contadorF--;
						}
					}
				}
				
			}
		}
		
	}
	
	private void contarMulheres( Selecionado [] x) throws UsuarioNaoEncontradoException{
		
		int esc0 = 0, esc1 = 0, esc2 = 0, esc3 = 0, esc4 = 0;
		Pessoa p = null;
		Funcionario temp = null;
		
		for(int i = 0; i < x.length; i++){
			p = Servidor.getInstance().procurar(x[i].getMatricula());
			temp = (Funcionario) p;
			
			if(temp.getSexo() == 'F'){
				switch(temp.getEscala()){
				
				case 1: {
					esc1 += x[i].getQtdExtras();
					break;
				}
				case 2: {
					esc2 += x[i].getQtdExtras();
					break;
				}
				case 3: {
					esc3 += x[i].getQtdExtras();
					break;
				}
				case 4: {
					esc4 += x[i].getQtdExtras();
					break;
				}
				case 0: {
					esc0 += x[i].getQtdExtras();
					break;
				}
				
				}
			}
		}
		
		
		//---------------------------------------------------------------------------------------
		do{
			if(esc0 != 0){
				if(esc1 < 14){
					for(int i = 0; i < this.qtdEscolhidos; i++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
						if(x[i].getEscala() == 0 & temp.getSexo() == 'F'){
							x[i].setEscala(1);
							esc0 = esc0 - x[i].getQtdExtras();
							esc1 = esc1 + x[i].getQtdExtras();
							break;
						}
					}
				}
				if(esc2 < 14){
					for(int i = 0; i < this.qtdEscolhidos; i++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
						if(x[i].getEscala() == 0 & temp.getSexo() == 'F'){
							x[i].setEscala(2);
							esc0 -= x[i].getQtdExtras();
							esc2 += x[i].getQtdExtras();
							break;
						}
					}
				}
				if(esc3 < 16){System.out.println("IF ESC 3");
					for(int i = 0; i < this.qtdEscolhidos; i++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
						if(x[i].getEscala() == 0 & temp.getSexo() == 'F'){
							x[i].setEscala(3);
							esc0 -= x[i].getQtdExtras();
							esc3 += x[i].getQtdExtras();
							break;
						}
					}
				}
				if(esc4 < 16){
					for(int i = 0; i < this.qtdEscolhidos; i++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
						if(x[i].getEscala() == 0 & temp.getSexo() == 'F'){
							x[i].setEscala(4);
							esc0 -= x[i].getQtdExtras();
							esc4 += x[i].getQtdExtras();
							break;
						}
					}
				}
				
				if(esc1 < 14 & esc2 < 14 & esc3 < 16 & esc4 < 16){
					esc0 = 0;
					System.out.println("IF ESC 0 recebendo 0");
				}
				System.out.println(esc1 + " -1- " + esc2 + " -2- " + esc3 + " -3- " + esc4 + " -4- " + esc0 + " -0-");
			}
		}while(esc0 != 0);
		System.out.println("Cheguei ao final");
		//OK AKI -------------------------------------------------------------------------------
			
		//---------------------------------------------------------------------------------------
			
		do{
			
		if(esc1 > 14){
			int conta1 = esc1 - 14, conta2 = esc2 - 14, conta3 = esc3 - 16, conta4 = esc4 - 16;
			int contar = 0;
			for(int i = 0; i < x.length; i++){
				p = Servidor.getInstance().procurar(x[i].getMatricula());
				temp = (Funcionario) p;
				if(temp.getSexo() == 'F'){
					if(temp.getEscala() == 1){
						if(!temp.isMotorista()){
							for(int j = 0; j < x[i].getQtdExtras(); j++){
								if(x[i].getDiasSorteados(j) == 0){
									contar++;
								}
								if(contar <= conta1){
									if(!temp.isTransferido()){
										if(conta2 < 0){
											int s = contar + conta2;
											if(s <= 0){
												x[i].setEscala(2);
												temp.setTransferido(true);
												conta2 += contar;
												conta1 -= contar;
												esc1 -= contar;
												esc2 += contar;
												contar = 0;
											}
											
										}
										else if(conta4 < 0){
											int s = contar + conta4;
											if(s <= 0){
												x[i].setEscala(4);
												temp.setTransferido(true);
												conta4 += contar;
												conta1 -= contar;
												esc1 -= contar;
												esc4 += contar;
												contar = 0;
											}
										}
										else{
											contar = 0;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if(esc3 > 16){
			int conta1 = esc1 - 14, conta2 = esc2 - 14, conta3 = esc3 - 16, conta4 = esc4 - 16;
			int contar = 0;
			for(int i = 0; i < x.length; i++){
				p = Servidor.getInstance().procurar(x[i].getMatricula());
				temp = (Funcionario) p;
				if(temp.getSexo() == 'F'){
					if(temp.getEscala() == 3){
						if(!temp.isMotorista()){
							for(int j = 0; j < x[i].getQtdExtras(); j++){
								if(x[i].getDiasSorteados(j) == 0){
									contar++;
								}
								if(contar <= conta3){
									if(!temp.isTransferido()){
										if(conta2 > 0){
											int s = contar + conta2;
											if(s <= 0){
												x[i].setEscala(2);
												temp.setTransferido(true);
												conta2 += contar;
												conta3 -= contar;
												esc3 -= contar;
												esc2 += contar;
												contar = 0;
											}
											
										}
										else if(conta4 < 0){
											int s = contar + conta4;
											if(s <= 0){
												x[i].setEscala(4);
												temp.setTransferido(true);
												conta4 += contar;
												conta3 -= contar;
												esc3 -= contar;
												esc4 += contar;
												contar = 0;
											}
										}
										else{
											contar = 0;
										}
									}
								}
							}
						}
					}
				}
			}
		}//BELEZAU ATE AKI -----------------------------
		
		if(esc4 > 16){
			int conta1 = esc1 - 14, conta2 = esc2 - 14, conta3 = esc3 - 16, conta4 = esc4 - 16;
			int contar = 0;
			for(int i = 0; i < x.length; i++){
				p = Servidor.getInstance().procurar(x[i].getMatricula());
				temp = (Funcionario) p;
				if(temp.getSexo() == 'F'){
					if(temp.getEscala() == 4){
						if(!temp.isMotorista()){
							for(int j = 0; j < x[i].getQtdExtras(); j++){
								if(x[i].getDiasSorteados(j) == 0){
									contar++;
								}
								if(contar <= conta4){
									if(!temp.isTransferido()){
										if(conta1 < 0){
											int s = contar + conta1;
											if(s <= 0){
												x[i].setEscala(1);
												temp.setTransferido(true);
												conta1 += contar;
												conta4 -= contar;
												esc4 -= contar;
												esc1 += contar;
												contar = 0;
											}
											
										}
										else if(conta3 < 0){
											int s = contar + conta3;
											if(s <= 0){
												x[i].setEscala(3);
												temp.setTransferido(true);
												conta3 += contar;
												conta4 -= contar;
												esc4 -= contar;
												esc3 += contar;
												contar = 0;
											}
										}
										else{
											contar = 0;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		if(esc2 > 14){
			int conta1 = esc1 - 14, conta2 = esc2 - 14, conta3 = esc3 - 16, conta4 = esc4 - 16;
			int contar = 0;
			for(int i = 0; i < x.length; i++){
				p = Servidor.getInstance().procurar(x[i].getMatricula());
				temp = (Funcionario) p;
				if(temp.getSexo() == 'F'){
					if(temp.getEscala() == 2){
						if(!temp.isMotorista()){
							for(int j = 0; j < x[i].getQtdExtras(); j++){
								if(x[i].getDiasSorteados(j) == 0){
									contar++;
								}
								if(contar <= conta2){
									if(!temp.isTransferido()){
										if(conta1 < 0){
											int s = contar + conta1;
											if(s <= 0){
												x[i].setEscala(1);
												temp.setTransferido(true);
												conta1 += contar;
												conta2 -= contar;
												esc2 -= contar;
												esc1 += contar;
												contar = 0;
											}
											
										}
										else if(conta3 < 0){
											int s = contar + conta3;
											if(s <= 0){
												x[i].setEscala(3);
												temp.setTransferido(true);
												conta3 += contar;
												conta2 -= contar;
												esc2 -= contar;
												esc3 += contar;
												contar = 0;
											}
										}
										else{
											contar = 0;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		}while(esc1 > 14 && esc2 > 14 && esc3 > 16 && esc4 > 16);
	}
	
	//OK
	public boolean preencherArray() throws UsuarioNaoEncontradoException{
		boolean resultado = false;
		int i;
		do{
			int contaaki = 0;
			for(i = 0; i < this.escolhidos.length; i ++){
			String x;
			int y, z;
			Scanner sc = new Scanner(System.in);
			System.out.println(contaaki++);
			System.out.println("Insira a matricula do escolhido:");
			x = sc.nextLine();//tem de identificar se a matricula existe no repositorio
			System.out.println("Insira a escala do escolhido:");
			y = sc.nextInt();
			System.out.println("Insira a quantidade de serviços do escolhido:");
			z = sc.nextInt();
			Selecionado temp = new Selecionado(x, y, z);
			this.escolhidos[i] = temp;
			
			}
			
			resultado = this.validarArray(this.escolhidos);//ajusta os numeros basicos.
			this.contarMotEscala(this.escolhidos);//ajusta escala 0 pra outras.
			
			for(int co = 0; co < this.qtdEscolhidos; co++){
				System.out.println(this.escolhidos[co].toString());
			}
			
			if(!resultado){
				System.out.println("REINICIANDO A SELEÇAO, INCOMPATIBILIDADE DE INFORMAÇÕES COM AS REGRAS BASICAS!!!");
			}
		}while(!resultado);
		
		
		
		return resultado;
		
	}
	
	

	public LocalDate getMesAno() {
		return mesAno;
	}

	public void setMesAno(LocalDate mesAno) {
		this.mesAno = mesAno;
	}

	public Selecionado getEscolhidos(int i) {
		return escolhidos[i];
	}
	
	public int getQtdEscolhidos(){
		return this.qtdEscolhidos;
	}

	
	

}