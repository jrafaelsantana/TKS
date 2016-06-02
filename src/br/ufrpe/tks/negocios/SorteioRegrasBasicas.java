package br.ufrpe.tks.negocios;

import br.ufrpe.tks.negocios.beans.Administrador;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;

public class SorteioRegrasBasicas {
	
	private Pessoa [] lista;
	
	public SorteioRegrasBasicas(Pessoa [] lista){
		this.lista = lista;
	}
	
	private int contarQtdServicos(Pessoa [] lista){//contando os serviços
		Funcionario temp;
		int contador = 0;
		for(int i = 0; i < this.lista.length; i++){
			if(this.lista[i] instanceof Funcionario){
				temp = (Funcionario) this.lista[i];
				contador += temp.escalaQtd();
			}
		}
		return contador;
	}
	
	public boolean conferirQtdServiços(Pessoa [] lista){//conferindo se tem 120
		int x = contarQtdServicos(lista);
		int contador = 120 - x;
		boolean resultado = false;
		if(contador == 0){
			resultado = true;
		}
		if(contador > 0){
			System.out.println("FALTAM: " + contador + " SERVIÇOS");
		}
		if(contador < 0){
			System.out.println("ESTÃO SOBRANDO: " + contador + "SERVIÇOS");
		}
		return resultado;
	}
	
	public boolean contarQtdMotorista(Pessoa [] lista){//conferindo se tem no minimo 60 serviços de motorista
		Funcionario temp;
		int contador = 0;
		boolean resultado = false;
		for(int i = 0; i < this.lista.length; i++){
			if(this.lista[i] instanceof Funcionario){
				temp = (Funcionario) this.lista[i];
				if(temp.isMotorista()== true){
					contador += temp.escalaQtd();	
				}
			}
	}
		if(contador >= 60){
			resultado = true;
		}
		return resultado;

	}
	
	private int suprirEscala(int valor){
//muda a var. escala de 0 pra qq outro num q falta e retorna o valor extrasqtd q foi movido
		Funcionario temp;
		int contador = 0;
		for(int i = 0; i < this.lista.length; i++){
			if(this.lista[i] instanceof Funcionario){
				temp = (Funcionario) this.lista[i];
				if(temp.isMotorista() == true && temp.getEscala() == 0){
					temp.setEscala(valor);
					contador = temp.escalaQtd();
				}
			}
		}
		return contador;
	}
	private void zeraVariavel(int a, int b, int c, int d, int e){
		a = 0;
		b = 0;
		c = 0;
		d = 0;
		e = 0;
	}
	
	public void alteraEscalaMotZero(Pessoa [] lista){//altera escala zero de motoristas para a escala q mais precise de serviços
		Funcionario temp;
		Administrador temp2;
		int escala1 = 0, escala2 = 0, escala3 = 0, escala4 = 0, escala0 = 0, voucontar = 0;
		do{
			voucontar = +1;//evitando loop infinito (talvez devesse retirar.)
			this.zeraVariavel(escala1, escala2, escala3, escala4, escala0);//zerando tudo pra nova contagem.
			for(int i = 0; i < this.lista.length; i++){
				if(this.lista[i] instanceof Funcionario){
					temp = (Funcionario) this.lista[i];
					if(temp.isMotorista() == true){
						switch(temp.getEscala()){
						case 1: escala1 += temp.escalaQtd(); break;//precisa de 14 serviços motorista
						case 2: escala2 += temp.escalaQtd(); break;//precisa de 14 serviços motorista
						case 3: escala3 += temp.escalaQtd(); break;//precisa de 16 serviços motorista
						case 4: escala4 += temp.escalaQtd(); break;//precisa de 16 serviços motorista
						case 5: escala0 += temp.escalaQtd(); break;
						} // somatorio total 16 + 16 + 14 + 14 = 60 serviços
					}
				}
			}//abaixo a mudanca da escala 0 pra completar escala 1 
			if(escala1 < 14 && escala1 < escala2 && escala1 < (escala3 - 2) && escala1 < (escala4 - 2)){
				int x = 1;
				escala0 -= this.suprirEscala(x);//escala0 tem valor total de extrasqtd subtraido do que foi movido
			}//fim 1.
			//abaixo a mudanca da escala 0 pra completar escala 2
			if(escala2 < 14 && escala2 < escala1 && escala2 < (escala3 - 2) && escala2 < (escala4 - 2)){
				int x = 2;
				escala0 -= this.suprirEscala(x);
			}//fim 2.
			//abaixo a mudanca da escala 0 pra completar escala 3
			if(escala3 < 16 && escala3 < (escala1 + 2) && escala3 < (escala2 + 2) && escala3 < escala4){
				int x = 3;
				escala0 -= this.suprirEscala(x);
			}//fim 3.
			if(escala4 < 16 && escala4 < (escala1 + 2) && escala4 < (escala2 + 2) && escala4 < escala3){
				int x = 4;
				escala0 -= this.suprirEscala(x);
			}//fim 4.
//rafael favor verificar o while, posso ter confundido algo e leia o proximo comentario.
		}while(escala0 > 0 || voucontar < 100 || escala1 < 14 && escala2 < 14 && escala3 < 16 && escala4 < 16);
	}//repeti a mudança de escala de 0 pra qq escala ate motoristas com escala 0 acabar ou as escalas todas ficarem com motoristas suficientes.
	
}