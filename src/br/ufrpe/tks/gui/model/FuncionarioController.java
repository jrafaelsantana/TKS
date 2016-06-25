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
	@FXML
	private Button btConsultarDias;
	@FXML
	private Button btEscalaCompleta;
	@FXML
	private ComboBox selectEscala;
	@FXML
	private TextField campoQtdServicos;
	@FXML
	private TextField campoDiasPreferidos;
	@FXML
	private Button btSalvarEscala;

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
			lbMotorista.setText("N�o");
			selectMotorista.setValue("N�o");
		}

		campoNome.setText(logado.getNome());
		campoMatricula.setText(logado.getMatricula());
		campoSenha.setText(logado.getSenha());
		selectSexo.getItems().addAll('M', 'F');
		selectSexo.setValue(logado.getSexo());
		selectMotorista.getItems().addAll("Sim", "N�o");
		selectCargo.getItems().addAll("Soldado", "Cabo", "Sargento", "Sub-tenente", "Tenente capit�o", "Major", "Tenente Coronel", "Coronel");
		selectCargo.setValue(logado.getCargo());
		
	}

	@FXML
	private void handleAtualizar() {
		try {
			if(!campoNome.getText().equals("") && !campoSenha.getText().equals("")){
				logado.setNome(campoNome.getText());
				logado.setSenha(campoSenha.getText());
				logado.setSexo((char) selectSexo.getValue());
				if (selectMotorista.getValue() == "Sim") {
					logado.setMotorista(true);
				} else if (selectMotorista.getValue() == "N�o") {
					logado.setMotorista(false);
				}
				logado.setCargo((String) selectCargo.getValue());
				Servidor.getInstance().salvardb();
				lbAviso.setText("Dados atualizados.");
			}else{
				lbAviso.setText("Erro. Algum campo n�o foi preenchido");
			}
		} catch (Exception e) {
			lbAviso.setText("Ocorreu um erro. Tente novamente.");
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleConsultarDias() {
		mainApp.showConsultarDias(logado);
	}

	@FXML
	private void handleEscalaCompleta() {
		mainApp.showConsultarEscalaCompleta(logado);
	}
	
	@FXML
	private void handleEscala(){
		
	}

	@FXML
	private void handleLogout() {
		mainApp.showLogin();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
}
