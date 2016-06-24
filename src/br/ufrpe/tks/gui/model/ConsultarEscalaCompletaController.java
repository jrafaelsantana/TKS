package br.ufrpe.tks.gui.model;

import br.ufrpe.tks.negocios.beans.Pessoa;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ConsultarEscalaCompletaController {
	@FXML
	private TableView tabelaEscala;
	
	private Stage dialogStage;
	private Pessoa pessoa;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setPerson(Pessoa p){
		
	}
}
