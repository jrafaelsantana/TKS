package br.ufrpe.tks.gui;

import java.io.IOException;

import br.ufrpe.tks.gui.model.FuncionarioController;
import br.ufrpe.tks.gui.model.LoginController;
import br.ufrpe.tks.negocios.beans.Administrador;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private Pessoa logado = null;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TKS");

		initRootLayout();
		
		if(logado == null){
			showLogin();
		}else if(logado instanceof Funcionario){
			showFuncionario((Funcionario) logado);
		}else if(logado instanceof Administrador){
			
		}
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
			AnchorPane login = (AnchorPane) loader.load();

			rootLayout.setCenter(login);
			LoginController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showFuncionario(Funcionario func){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Funcionario.fxml"));
			AnchorPane funcionario = (AnchorPane) loader.load();

			rootLayout.setCenter(funcionario);
			FuncionarioController controller = loader.getController();
			controller.setMainApp(this);
			
			controller.setPessoa(func);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage(){
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
