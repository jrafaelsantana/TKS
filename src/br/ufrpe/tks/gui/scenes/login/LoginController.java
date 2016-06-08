package br.ufrpe.tks.gui.scenes.login;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.tks.dados.RepositorioPessoa;
import br.ufrpe.tks.exceptions.LoginIncorretoException;
import br.ufrpe.tks.exceptions.SenhaIncorretaException;
import br.ufrpe.tks.negocios.CadastroPessoa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginController implements Initializable {

	@FXML
	private TextField campoMatricula;
	@FXML
	private PasswordField campoSenha;
	@FXML
	private Button btEntrar;
	@FXML
	private Label lbAviso;
	
	@FXML
	private void btEntrar(ActionEvent event){
		//Verificar se os campos estão vazios
		if(campoMatricula.getText().equals("") || campoSenha.getText().equals("")){
			lbAviso.setTextFill(Color.RED);
			lbAviso.setText("Preencha os campos corretamente.");
		}else{
			CadastroPessoa cadastroPessoa = new CadastroPessoa(RepositorioPessoa.getInstance());
			try{
				cadastroPessoa.login(campoMatricula.getText(), campoSenha.getText());
			}catch(LoginIncorretoException | SenhaIncorretaException e){
				lbAviso.setText(e.getMessage());
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
