package br.ufrpe.tks.gui.model;

import br.ufrpe.tks.negocios.Servidor;
import br.ufrpe.tks.negocios.beans.Administrador;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdministradorEditarPessoaController {
	@FXML
	private TextField campoNomeEditar;
	@FXML
	private ComboBox selectCargoEditar;
	@FXML
	private ComboBox selectSexoEditar;
	@FXML
	private TextField campoMatriculaEditar;
	@FXML
	private ComboBox selectMotoristaEditar;
	@FXML
	private TextField campoSenhaEditar;
	@FXML
	private Button btAtualizaEditar;
	@FXML
	private Label lbAvisoEditar;

	private Stage dialogStage;
	private Pessoa pessoa;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setPerson(Pessoa pessoa) {
		if (pessoa instanceof Administrador) {
			this.pessoa = pessoa;

			campoNomeEditar.setText(pessoa.getNome());
			campoMatriculaEditar.setText(pessoa.getMatricula());
			campoSenhaEditar.setText(pessoa.getSenha());
			selectSexoEditar.getItems().addAll('M', 'F');
			selectSexoEditar.setValue(pessoa.getSexo());
			selectMotoristaEditar.setDisable(true);
			selectCargoEditar.setDisable(true);
		} else if (pessoa instanceof Funcionario) {
			this.pessoa = (Funcionario) pessoa;

			campoNomeEditar.setText(pessoa.getNome());
			campoMatriculaEditar.setText(pessoa.getMatricula());
			campoSenhaEditar.setText(pessoa.getSenha());
			selectSexoEditar.getItems().addAll('M', 'F');
			selectSexoEditar.setValue(pessoa.getSexo());
			if (((Funcionario) pessoa).isMotorista()) {
				selectMotoristaEditar.setValue("Sim");
			} else {
				selectMotoristaEditar.setValue("Não");
			}
			selectMotoristaEditar.getItems().addAll("Sim", "Não");
			selectCargoEditar.getItems().addAll("Soldado", "Cabo", "Sargento", "Sub-tenente", "Tenente capitão",
					"Major", "Tenente Coronel", "Coronel");
			selectCargoEditar.setValue(((Funcionario) pessoa).getCargo());
		}
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleAtualizarEditar() {
		try {
			if (!campoNomeEditar.getText().equals("") && !campoSenhaEditar.getText().equals("")) {
				pessoa.setNome(campoNomeEditar.getText());
				pessoa.setSenha(campoSenhaEditar.getText());
				pessoa.setSexo((char) selectSexoEditar.getValue());

				if (pessoa instanceof Funcionario) {
					if (selectMotoristaEditar.getValue() == "Sim") {
						((Funcionario) pessoa).setMotorista(true);
					} else if (selectMotoristaEditar.getValue() == "Não") {
						((Funcionario) pessoa).setMotorista(false);
					}
					((Funcionario) pessoa).setCargo((String) selectCargoEditar.getValue());
				} else if (pessoa instanceof Administrador) {

				}
				lbAvisoEditar.setText("Dados atualizados.");
				Servidor.getInstance().salvardb();
			} else {
				lbAvisoEditar.setText("Erro. Algum campo não foi preenchido");
			}
		} catch (Exception e) {
			lbAvisoEditar.setText("Ocorreu um erro. Tente novamente.");
			e.printStackTrace();
		}
	}
}