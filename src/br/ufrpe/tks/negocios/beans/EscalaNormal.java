package br.ufrpe.tks.negocios.beans;

public class EscalaNormal {
	
	private int [] diasBloqueados;// dias q nao podem ser sorteados
	private int escala; // determina qual serie de dias sera posta no array de blok
	private int [] extrasQtd; //determina qtd de extras
	private int transferido; 
	//define se ja foi mexido na escala pra nao mexer demais e ser sorteado o dia block
	
	public EscalaNormal(int x, int qtd){
		
		int [] diasBloqueados = new int[8];
		this.escala = x;
		this.transferido = 1;
		int [] extrasQtd = new int [qtd];// qtd eh a qtd de dias q se voluntariou no mes
		
		switch(x){
		case 1: {
			diasBloqueados[0] = 1;
			diasBloqueados[1] = 5;
			diasBloqueados[2] = 9;
			diasBloqueados[3] = 13;
			diasBloqueados[4] = 17;
			diasBloqueados[5] = 21;
			diasBloqueados[6] = 25;
			diasBloqueados[7] = 29;
			break;
		}
		
		case 2: {
			
			diasBloqueados[0] = 2;
			diasBloqueados[1] = 6;
			diasBloqueados[2] = 10;
			diasBloqueados[3] = 14;
			diasBloqueados[4] = 18;
			diasBloqueados[5] = 22;
			diasBloqueados[6] = 26;
			diasBloqueados[7] = 30;
			break;
		}
		
		case 3: {
			
			diasBloqueados[0] = 3;
			diasBloqueados[1] = 7;
			diasBloqueados[2] = 11;
			diasBloqueados[3] = 15;
			diasBloqueados[4] = 19;
			diasBloqueados[5] = 23;
			diasBloqueados[6] = 27;
			diasBloqueados[7] = 31;
			break;
		}
		
		case 4: {
			
			diasBloqueados[0] = 4;
			diasBloqueados[1] = 8;
			diasBloqueados[2] = 12;
			diasBloqueados[3] = 16;
			diasBloqueados[4] = 20;
			diasBloqueados[5] = 24;
			diasBloqueados[6] = 28;
			diasBloqueados[7] = 0;
			break;
			
		}
		
		case 0: {
			
			diasBloqueados[0] = 0;
			diasBloqueados[1] = 0;
			diasBloqueados[2] = 0;
			diasBloqueados[3] = 0;
			diasBloqueados[4] = 0;
			diasBloqueados[5] = 0;
			diasBloqueados[6] = 0;
			diasBloqueados[7] = 0;
			break;
			
		}
		
		}
	}
	public int getEscala(){
		return this.escala;
	}
	
	public void setEscala(int x){
		this.escala = x;
	}
	
	public int getExtrasQtd(){
		return this.extrasQtd.length;
	}
	
	public void transferidoMais(){//mesma coisa da funcao abaixo
		
		diasBloqueados[0] += 1;
		diasBloqueados[1] += 1;
		diasBloqueados[2] += 1;
		diasBloqueados[3] += 1;
		diasBloqueados[4] += 1;
		diasBloqueados[5] += 1;
		diasBloqueados[6] += 1;
		diasBloqueados[7] += 1;
		this.transferido += 1;
		
	}

	public void transferidoMenos(){//mudar os dias block pra sorteio ficar mais suave
		
		diasBloqueados[0] -= 1;
		diasBloqueados[1] -= 1;
		diasBloqueados[2] -= 1;
		diasBloqueados[3] -= 1;
		diasBloqueados[4] -= 1;
		diasBloqueados[5] -= 1;
		diasBloqueados[6] -= 1;
		diasBloqueados[7] -= 1;
		this.transferido -= 1;
	}
	
	public boolean isTransferido(){
		boolean resultado = false;
		if(this.transferido != 1){
			resultado = true;
		}
		return resultado;
	}
	
	public void showExtras(int extrasQtd[]){//mostrar ao funcionario os dias sorteados
		for(int mostra : extrasQtd){
			System.out.println(mostra);
		}
	}
	
	public void validarDiasDiferentes(int extras[]){
		for(int i = 0; i < this.extrasQtd.length; i++){
			for(int j = 0; j < this.extrasQtd.length; j++){
				if(this.extrasQtd[i] == this.extrasQtd[j] && i != j){
					this.extrasQtd[j] = 0;
				}
			}
		}
	}

}
