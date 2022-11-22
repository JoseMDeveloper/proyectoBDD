package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OwnerSceneController extends PrincipalAbstractController implements Initializable{

	@FXML
    private Button arriendaConNosptros;
	public void sidebar(MouseEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/source/sidebar.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
	}
	public void arrienda(MouseEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/source/propiedadDueno.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
	}
    
}
