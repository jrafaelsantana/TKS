package com.tks.negocio;
import com.tks.negocio.beans.Funcionario;

public class ServidorBanco {
	private CadastroFuncionario funcionario;
	
	private static ServidorBanco instance;
	
	private ServidorBanco(){
		this.funcionario = new CadastroFuncionario();
	}
	
	public static ServidorBanco getInstance() {
        if (instance == null) {
            instance = new ServidorBanco();
        }
        return instance;
    }
	
	public void cadastrarFuncionario(Funcionario f){
		funcionario.cadastrar(f);
	}
	
	public void cadastrarFuncionario(String nome, String cargo, char sexo, int matricula, boolean motorista){
		Funcionario f = new Funcionario(nome,cargo,sexo,matricula,motorista);
		funcionario.cadastrar(f);
	}
	
	public void removerFuncionario(int matricula){
		funcionario.apagar(matricula);
	}
	
	public Funcionario procurarFuncionario(int matricula){
		return funcionario.procurar(matricula);
	}
}