package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

public class sidebarController implements Initializable{
	@FXML
	private AnchorPane anchorpanecuenta;
	@FXML
	private AnchorPane anchorpanenoti;
	@FXML
	private Button btnNotificaciones;
	
	@FXML
	private Button btnCerrarSesion;
	@FXML
	private Button btnMiCuenta;
	
	@FXML
	private Button perfil;
	
	@FXML
	private BorderPane bp;
	
	@FXML
	private AnchorPane ap;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		anchorpanecuenta.setVisible(false);
		anchorpanenoti.setVisible(false);

	}
	@FXML
	public void quitarnoti(MouseEvent event) {
		anchorpanenoti.setVisible(false);
	}
	
	@FXML
	public void mostrarnoti(MouseEvent event) {
		anchorpanenoti.setVisible(true);
		FadeTransition fade=new FadeTransition();
		fade.setNode(anchorpanenoti);
		fade.setDuration(Duration.millis(300));
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
	}
	
	@FXML
	public void quitarOpcionesCuenta(MouseEvent event) {
		anchorpanecuenta.setVisible(false); 
	}

	@FXML
	public void cerrarSesion(MouseEvent event) {
		Platform.exit();
	}
	
	@FXML
	public void cargarperfil(MouseEvent event) {
		cargarPagina("perfil");
	}
	
	@FXML
	public void mostrarOpcionesCuenta(MouseEvent event) {
		anchorpanecuenta.setVisible(true);
		FadeTransition fade=new FadeTransition();
		fade.setNode(anchorpanecuenta);
		fade.setDuration(Duration.millis(300));
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
	}
	private void cargarPagina(String string)
	{
		Parent root =null;
		try
		{
			root=FXMLLoader.load(getClass().getResource("/source/"+string+".fxml"));
			
		}
		catch(IOException ex)
		{
			
		}
		bp.setCenter(root);
	}
}
