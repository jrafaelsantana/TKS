package br.ufrpe.tks.dados;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import br.ufrpe.tks.negocios.beans.Administrador;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;

public class RepositorioPessoa implements IRepositorioPessoa, Serializable {

	private static final long serialVersionUID = -6495110561477281438L;
	private static RepositorioPessoa instance;
	private ArrayList<Pessoa> pessoa;

	Pessoa pessoa1 = new Funcionario("José Rafael", "Cabo", 'M', "12345", true, "1415");
	Pessoa pessoa2 = new Funcionario("Éder Lucena", "Major", 'F', "98765", false, "123");
	Pessoa administrador = new Administrador("Administrador", 'M', "Admin", "Admin");
	
	Pessoa pessoa01 = new Funcionario("José", "Cabo", 'M', "1", true, "1415");//5
	Pessoa pessoa02 = new Funcionario("Éder", "Major", 'M', "2", false, "123");//20
	Pessoa pessoa03 = new Funcionario("Mariano", "Cabo", 'M', "3", true, "1415");//5
	Pessoa pessoa04 = new Funcionario("Ednaldo", "Major", 'M', "4", false, "123");//20
	Pessoa pessoa05 = new Funcionario("Vitor", "Cabo", 'M', "5", true, "1415");//1 ------ 0
	Pessoa pessoa06 = new Funcionario("Lucinda", "Major", 'F', "6", false, "123");//4
	Pessoa pessoa07 = new Funcionario("Gabriela", "Cabo", 'F', "7", true, "1415");//5
	Pessoa pessoa08 = new Funcionario("Adriano", "Major", 'F', "8", false, "123");//5
	Pessoa pessoa09 = new Funcionario("Marinho", "Cabo", 'M', "9", true, "1415");//5
	Pessoa pessoa10 = new Funcionario("Kenya", "Major", 'F', "10", true, "123");//5 --- 30
	Pessoa pessoa11 = new Funcionario("Vitorio", "Cabo", 'M', "11", true, "1415");//10
	Pessoa pessoa12 = new Funcionario("Diogo", "Major", 'M', "12", true, "123");//5
	Pessoa pessoa13 = new Funcionario("Pakura", "Cabo", 'M', "13", true, "1415");//5
	Pessoa pessoa14 = new Funcionario("Sakura", "Major", 'M', "14", true, "123");//4
	Pessoa pessoa15 = new Funcionario("kabuto", "Cabo", 'M', "15", true, "1415");//10 -- 0
	Pessoa pessoa16 = new Funcionario("Mirela", "Major", 'F', "16", false, "123");//5
	Pessoa pessoa17 = new Funcionario("Jacira", "Cabo", 'F', "17", false, "1415");//4 
	Pessoa pessoa18 = new Funcionario("Dinoau", "Major", 'M', "18", true, "123");//7
	Pessoa pessoa19 = new Funcionario("Mario", "Cabo", 'M', "19", true, "1415");//6
	Pessoa pessoa20 = new Funcionario("Georgia", "Major", 'F', "20", true, "123");//16 -- 23
	

	public static RepositorioPessoa getInstance() {
		if (instance == null) {
			instance = RepositorioPessoa.carregarbd();
		}
		return instance;
	}

	private RepositorioPessoa() {
		this.pessoa = new ArrayList<Pessoa>();

		pessoa.add(pessoa1);
		pessoa.add(pessoa2);
		pessoa.add(administrador);
		pessoa.add(pessoa01);
		pessoa.add(pessoa02);
		pessoa.add(pessoa03);
		pessoa.add(pessoa04);
		pessoa.add(pessoa05);
		pessoa.add(pessoa06);
		pessoa.add(pessoa07);
		pessoa.add(pessoa08);
		pessoa.add(pessoa09);
		pessoa.add(pessoa10);
		pessoa.add(pessoa11);
		pessoa.add(pessoa12);
		pessoa.add(pessoa13);
		pessoa.add(pessoa14);
		pessoa.add(pessoa15);
		pessoa.add(pessoa16);
		pessoa.add(pessoa17);
		pessoa.add(pessoa18);
		pessoa.add(pessoa19);
		pessoa.add(pessoa20);
	}

	@Override
	public void cadastrar(Pessoa p) {
		if (p != null) {
			this.pessoa.add(p);
		}
	}

	@Override
	public void remover(Pessoa p) {
		if (p != null) {
			this.pessoa.remove(p);
		}
	}

	@Override
	public Pessoa procurar(String matricula) {
		Pessoa pessoaP = null;
		if (this.pessoa.size() > 0) {
			for (int i = 0; i < this.pessoa.size(); i++) {
				if (matricula.equals(this.pessoa.get(i).getMatricula())) {
					pessoaP = this.pessoa.get(i);
				}
			}
		}
		return pessoaP;
	}

	@Override
	public ArrayList<Pessoa> getUsuarios() {
		return this.pessoa;
	}

	private static RepositorioPessoa carregarbd() {

		RepositorioPessoa repositorio = null;

		File bd = new File("RepositorioPessoa.tks");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {

			fis = new FileInputStream(bd);
			ois = new ObjectInputStream(fis);

			repositorio = (RepositorioPessoa) ois.readObject();
		} catch (Exception e) {
			repositorio = new RepositorioPessoa();

			try {
				if (!bd.exists()) {
					bd.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(bd);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(repositorio);
				oos.flush();
				oos.close();
				fos.flush();
				fos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			//e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println("Não foi possível fechar o arquivo!");
					e.printStackTrace();
				}
			}
		}

		return repositorio;
	}

	@Override
	public void salvarbd() {
		if (!(instance == null)) {

			File bd = new File("RepositorioPessoa.tks");

			try {

				if (!bd.exists()) {
					bd.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(bd);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(instance);
				oos.flush();
				oos.close();
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}