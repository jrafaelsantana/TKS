package br.ufrpe.tks.gui.scenes.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application{
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("br.ufrpe.tks.gui.scenes.login.Login.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setTitle("Login");
		primaryStage.setScene(cena);
		primaryStage.show();
	}

}
