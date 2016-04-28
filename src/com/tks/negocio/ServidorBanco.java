package com.tks.negocio;

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
	
	public void cadastrarFuncionario(){
		funcionario.cadastrarFuncionario();
	}
	
	public void removerFuncionario(){
		funcionario.removerFuncionario();
	}
}