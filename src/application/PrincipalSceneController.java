package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrincipalSceneController implements Initializable{
	
	@FXML private Button btnBuscarPropiedades;
	@FXML private Button btnNotificaciones;
	@FXML private Button btnInfo;
	@FXML private Button btnMiCuenta;
	
	@Override
 	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void changeToSearchScene(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/source/SearchScene.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
}
