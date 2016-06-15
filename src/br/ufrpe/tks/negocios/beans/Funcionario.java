package br.ufrpe.tks.negocios.beans;


public class Funcionario extends Pessoa {
	
	private String cargo;
	private boolean motorista;
	private EscalaNormal escala;
	private boolean transferido;
	
	public Funcionario(String nome, String cargo, char sexo, String matricula, boolean motorista, String senha){
		super(nome, sexo, matricula, senha);
		this.cargo = cargo;
		this.motorista = motorista;
	}
	
	public int getEscala(){
		return this.escala.getEscala();
	}
	
	public void setEscala(int x){
		this.escala.setEscala(x);
	}
	
	public int escalaQtd(){
		return escala.getExtrasQtd();
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public boolean isMotorista() {
		return motorista;
	}

	public void setMotorista(boolean motorista) {
		this.motorista = motorista;
	}
	
	public boolean equals(Funcionario f){
		boolean resultado=false;
		
		if(((Pessoa)f).getMatricula() == ((Pessoa)this).getMatricula()){
			resultado=true;
		}
		
		return resultado;
	}
	
	public boolean isTransferido(){
		return transferido;
	}
	
	public void setTransferido(boolean x){
		this.transferido = x;
	}
	
	public String toString(){
		return super.toString() + 
				"Cargo: " + this.cargo + "\n" +
				"Motorista: " + this.motorista + "\n";
	}
}