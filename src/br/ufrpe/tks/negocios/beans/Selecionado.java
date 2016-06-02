package br.ufrpe.tks.negocios.beans;

public class Selecionado {
	
	private int matricula;
	private int [] diasSorteados;
	private int escala;
	private int qtdExtras;
	
	
	public Selecionado(int matricula, int escala, int qtdExtras){
		this.diasSorteados = new int[qtdExtras];
		this.matricula = matricula;
		this.escala = escala;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public int getDiasSorteados(int i) {
		return diasSorteados[i];
	}

	public void setDiasSorteados(int i, int diasSorteados) {
		this.diasSorteados[i] = diasSorteados;
	}

	public int getEscala() {
		return escala;
	}

	public void setEscala(int escala) {
		this.escala = escala;
	}

	public int getQtdExtras() {
		return qtdExtras;
	}

	public void setQtdExtras(int qtdExtras) {
		this.qtdExtras = qtdExtras;
	}
	
}