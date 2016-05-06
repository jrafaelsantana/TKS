package com.tks.negocio;
import com.tks.negocio.beans.Funcionario;

public class Servidor {
	private CadastroFuncionario funcionario;
	private Sorteio sorteio;
	
	private static Servidor instance;
	
	private Servidor(){
		this.funcionario = new CadastroFuncionario();
		this.sorteio = new Sorteio();
	}
	
	public static Servidor getInstance() {
        if (instance == null) {
            instance = new Servidor();
        }
        return instance;
    }
	
	public void cadastrarFuncionario(Funcionario f){
		funcionario.cadastrar(f);
	}
	
	public void cadastrarFuncionario(String nome, String cargo, char sexo, int matricula, boolean motorista, int qtdCotas){
		Funcionario f = new Funcionario(nome,cargo,sexo,matricula,motorista,qtdCotas);
		funcionario.cadastrar(f);
	}
	
	public void removerFuncionario(int matricula){
		funcionario.apagar(matricula);
	}
	
	public Funcionario procurarFuncionario(int matricula){
		return funcionario.procurar(matricula);
	}
	
	public void sorteio(){
		sorteio.realizaSorteio();
	}
	
	public Funcionario[][] arraySorteio(){
		return sorteio.getArray();
	}
}