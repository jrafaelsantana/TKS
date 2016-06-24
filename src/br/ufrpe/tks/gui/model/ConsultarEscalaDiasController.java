package br.ufrpe.tks.gui.model;

import br.ufrpe.tks.negocios.beans.Pessoa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConsultarEscalaDiasController {
	@FXML
	private Label lbDiasSorteados;
	@FXML
	private Label lbDias;
	@FXML
	private Button btFechar;
	
	private Stage dialogStage;
	private Pessoa pessoa;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void handleFechar(){
		dialogStage.close();
	}
	
	public void setPerson(Pessoa p){
		
	}
}
