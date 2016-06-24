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