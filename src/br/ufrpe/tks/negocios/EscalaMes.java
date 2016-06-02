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
	
	private boolean validarArray(Selecionado [] escolhidos) throws UsuarioNaoEncontradoException{
		int count = 0;
		boolean resultado = true;
		Selecionado temp;
		for(int i = 0; i < escolhidos.length; i ++){
			temp = escolhidos[i];
			count += temp.getQtdExtras();
			if(i == escolhidos.length && count < 120){//CONTANDO SERVIÇOS TOTAIS.
				System.out.println("ERRO!!! EXITEM MENOS DE 120 SERVICOS");
				resultado = false;
			}
			if(i == escolhidos.length){
				int extrasMotorista = 0;
				int contaMulheres = 0;
				for(int seilah = 0; seilah < escolhidos.length; seilah++){
					
					int m = escolhidos[seilah].getMatricula();
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
	
	private void contarMotEscala(Selecionado [] x) throws UsuarioNaoEncontradoException{//contar motoristas por escalas. 
		int esc1 = 0, esc2 = 0, esc3 = 0, esc4 = 0, esc0 = 0;
		Funcionario temp = null;
		for(int i = 0; i < x.length; i++){
				temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
				if(temp.isMotorista()){
					switch(x[i].getEscala()){
					case 1: {
						esc1 += x[i].getQtdExtras();
						break;
					}
					case 2: {
						esc2 += x[i].getQtdExtras();
					}
					case 3: {
						esc3 += x[i].getQtdExtras();
					}
					case 4: {
						esc4 += x[i].getQtdExtras();
					}
					case 0: {
						esc0 += x[i].getQtdExtras();
					}
					}
				}
				
			}
		
		do{
			if(esc0 != 0){
				if(esc1 < 14){
					for(int i = 0; i < this.qtdEscolhidos; i++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
						if(x[i].getEscala() == 0 && temp.isMotorista()){
							x[i].setEscala(1);
							esc0 -= x[i].getQtdExtras();
							esc1 += x[i].getQtdExtras();
							break;
						}
					}
				}
				if(esc2 < 14){
					for(int i = 0; i < this.qtdEscolhidos; i++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
						if(x[i].getEscala() == 0 && temp.isMotorista()){
							x[i].setEscala(2);
							esc0 -= x[i].getQtdExtras();
							esc2 += x[i].getQtdExtras();
							break;
						}
					}
				}
				if(esc3 < 16){
					for(int i = 0; i < this.qtdEscolhidos; i++){
						temp = (Funcionario) Servidor.getInstance().procurar(x[i].getMatricula());
						if(x[i].getEscala() == 0 && temp.isMotorista()){
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
						if(x[i].getEscala() == 0 && temp.isMotorista()){
							x[i].setEscala(4);
							esc0 -= x[i].getQtdExtras();
							esc4 += x[i].getQtdExtras();
							break;
						}
					}
				}
				if(esc1 == 14 && esc2 == 14 && esc3 == 16 && esc4 == 16){
					esc0 = 0;
				}
			}
		}while(esc0 != 0);
//AO FINAL DISTRIBUI A ESCALA zero PRA QQ ESCALA QUE ESTEJA FALTANDO MOTORISTA, ATE ACABAR esc0.
	}//OU ATE ACABAR COMPLETANDO TODAS AS ESCALAS
	
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
	
	public boolean preencherArray() throws UsuarioNaoEncontradoException{
		boolean resultado = false;
		int i;
		do{
			for(i = 0; i < this.escolhidos.length; i ++){
			int x, y, z;
			Scanner sc = new Scanner(System.in);
			System.out.println("Insira a matricula do escolhido:");
			x = sc.nextInt();//tem de identificar se a matricula existe no repositorio
			System.out.println("Insira a escala do escolhido:");
			y = sc.nextInt();
			System.out.println("Insira a quantidade de serviços do escolhido:");
			z = sc.nextInt();
			Selecionado temp = new Selecionado(x, y, z);
			this.escolhidos[i] = temp;
			sc.close();
			}
			resultado = this.validarArray(this.escolhidos);
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