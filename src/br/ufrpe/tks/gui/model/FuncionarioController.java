package br.ufrpe.tks.gui.model;

import br.ufrpe.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.tks.gui.MainApp;
import br.ufrpe.tks.negocios.Servidor;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FuncionarioController {
	@FXML
	private Label lbNome;
	@FXML
	private Label lbCargo;
	@FXML
	private Label lbSexo;
	@FXML
	private Label lbMatricula;
	@FXML
	private Label lbMotorista;
	@FXML
	private Label lbErro;
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
	private Button btAtualizar;
	@FXML
	private Button btLogout;

	private Funcionario logado;
	private Stage funcionarioStage;
	private MainApp mainApp;

	@FXML
	private void initialize() {
	}

	public void setFuncionarioStage(Stage funcionarioStage) {
		this.funcionarioStage = funcionarioStage;
	}

	public void setPessoa(Pessoa pessoa) {
		this.logado = (Funcionario) pessoa;

		lbNome.setText(logado.getNome());
		lbCargo.setText(logado.getCargo());
		if (logado.getSexo() == 'M') {
			lbSexo.setText("Masculino");
		} else {
			lbSexo.setText("Feminino");
		}
		lbMatricula.setText(logado.getMatricula());
		if (logado.isMotorista()) {
			lbMotorista.setText("Sim");
			selectMotorista.setValue("Sim");
		} else {
			lbMotorista.setText("Não");
			selectMotorista.setValue("Não");
		}

		campoNome.setText(logado.getNome());
		campoMatricula.setText(logado.getMatricula());
		campoSenha.setText(logado.getSenha());
		selectSexo.getItems().addAll('M', 'F');
		selectSexo.setValue(logado.getSexo());
		selectMotorista.getItems().addAll("Sim", "Não");
		selectCargo.getItems().addAll("Soldado", "Cabo", "Sargento", "Sub-tenente", "Tenente capitão", "Major", "Tenente Coronel", "Coronel");
		selectCargo.setValue(logado.getCargo());
	}

	@FXML
	private void handleAtualizar() {
		try {
			logado.setNome(campoNome.getText());
			logado.setSenha(campoSenha.getText());
			logado.setSexo((char) selectSexo.getValue());
			if (selectMotorista.getValue() == "Sim") {
				logado.setMotorista(true);
			} else if (selectMotorista.getValue() == "Não") {
				logado.setMotorista(false);
			}
			logado.setCargo((String) selectCargo.getValue());
			lbAviso.setText("Dados atualizados.");
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
	/*
	 * private boolean validaAtualizacao(){ String erro = ""; }
	 */
}
