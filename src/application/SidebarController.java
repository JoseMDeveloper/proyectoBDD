package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dataClass.Sesion;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

public class SidebarController extends PrincipalAbstractController implements Initializable{
	
	@FXML
	private Button perfil;
	
	@FXML
	private BorderPane bp;
	
	@FXML
	private AnchorPane ap;
	
	@FXML
    private ImageView pantallaPrinci;
	
	@FXML
	public void cargarperfil(MouseEvent event) {
		cargarPagina("perfil");
	}
	
	
	@FXML
	public void cargarvisits(MouseEvent event) {
		cargarPagina("visitas");
	}
	
	private void cargarPagina(String string){
		Parent root =null;
		try{
			root=FXMLLoader.load(getClass().getResource("/source/"+string+".fxml"));
		}
		catch(IOException ex){}
		bp.setCenter(root);
	}
	
	@FXML
	public void cambiaVentanaPrincipal(MouseEvent event) throws IOException {
		if(Sesion.getUser().getIDtipousuario()==1)
		{
			Parent root = FXMLLoader.load(getClass().getResource("/source/ClientScene.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.centerOnScreen();
			
		}
		else if(Sesion.getUser().getIDtipousuario()==2)
		{
			Parent root = FXMLLoader.load(getClass().getResource("/source/OwnerScene.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.centerOnScreen();
		}
		if(Sesion.getUser().getIDtipousuario()==3)
		{
			Parent root = FXMLLoader.load(getClass().getResource("/source/ClientScene.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.centerOnScreen();
			
		}
		
//		ventanaPrinci.setX(-10);
//		ventanaPrinci.setY(0);
//		stage.setMaximized(true);
//		stage.setResizable(false);
//		ventanaPrinci.showAndWait();
	}
}
