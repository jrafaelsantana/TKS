package br.ufrpe.tks.gui.model;

import br.ufrpe.tks.dados.IRepositorioPessoa;
import br.ufrpe.tks.dados.RepositorioPessoa;
import br.ufrpe.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.tks.gui.MainApp;
import br.ufrpe.tks.negocios.CadastroPessoa;
import br.ufrpe.tks.negocios.beans.Administrador;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdministradorController {
	@FXML
	private Label lbAviso;
	@FXML
	private TextField campoNome;
	@FXML
	private TextField campoMatricula;
	@FXML
	private PasswordField campoSenha;
	@FXML
	private ComboBox selectCargo;
	@FXML
	private ComboBox selectSexo;
	@FXML
	private ComboBox selectMotorista;
	@FXML
	private ComboBox selectNivel;
	@FXML
	private Button btCadastrar;
	@FXML
	private Button btLogout;

	private Administrador logado;
	private Stage administradorStage;
	private MainApp mainApp;

	IRepositorioPessoa repositorioPessoa = RepositorioPessoa.getInstance();
	CadastroPessoa cadastroPessoa = new CadastroPessoa(repositorioPessoa);

	@FXML
	private void initialize() {
	}

	public void setAdministradorStage(Stage administradorStage) {
		this.administradorStage = administradorStage;
	}

	public void setPessoa(Pessoa pessoa) {
		this.logado = (Administrador) pessoa;
		selectMotorista.getItems().addAll("Sim", "Não");
		selectSexo.getItems().addAll('M', 'F');
		selectNivel.getItems().addAll("Funcionário", "Administrador");
		selectCargo.getItems().addAll("Soldado", "Cabo", "Sargento", "Sub-tenente", "Tenente capitão", "Major",
				"Tenente Coronel", "Coronel");
	}

	@FXML
	private void handleCadastrar() {
		try {
			if(campoNome.getText().equals("") || campoMatricula.getText().equals("") || campoSenha.getText().equals("")){
				try{
					cadastroPessoa.procurar(campoMatricula.getText());
					lbAviso.setText("Matrícula já cadastrada.");
				}catch(UsuarioNaoEncontradoException e){
					if(selectNivel.getValue().toString() == "Funcionário"){
						lbAviso.setText("Funcionário");
					}else if(selectNivel.getValue().toString() == "Administrador"){
						lbAviso.setText("Administrador");
					}
				}
			}else{
				lbAviso.setText("Preencha todos os campos.");
			}
		} catch (Exception e) {
			lbAviso.setText("Ocorreu um erro. Tente novamente.");
			e.printStackTrace();
		}
	}

	@FXML
	private void handleLogout() {
		mainApp.showLogin();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}