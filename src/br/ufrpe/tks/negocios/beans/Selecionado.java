package br.ufrpe.tks.negocios.beans;

public class Selecionado {
	
	private String matricula;
	private int [] diasSorteados;
	private int escala;
	private int qtdExtras;
	
	
	public Selecionado(String matricula, int escala, int qtdExtras){
		this.diasSorteados = new int[qtdExtras];
		this.matricula = matricula;
		this.escala = escala;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
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