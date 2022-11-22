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
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class PrincipalAbstractController implements Initializable{

    @FXML
    protected Button btnMiCuenta;
    
    @FXML
    private ListView<?> notificaciones;

    @FXML
    private AnchorPane opcionesMiCuenta;
	
	@FXML
	protected Button btnCerrarSesion;
	
	@FXML
    protected Button btnVisitas;
	
	@FXML
	protected Button btnNotificaciones;
	
	@FXML
	protected ImageView cerrar;
	
	@FXML
	protected AnchorPane anchorPaneCuenta;
	
	@FXML
	protected AnchorPane anchorPaneNotificaciones;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		opcionesMiCuenta.setVisible(false);
		notificaciones.setVisible(false);
	}
	
	@FXML
	public void cerrarSesion(MouseEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Cerrar Sesion");
        alert.setContentText("Se ha cerrado sesion correctamente");
        alert.showAndWait();
        
        Sesion.setUser(null);
        
        Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/source/LoginScene.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e) {
			Alert alert1 = new Alert(Alert.AlertType.ERROR);
	        alert1.setHeaderText(null);
	        alert1.setTitle("Error");
	        alert1.setContentText("Se ha cerrado sesion correctamente");
	        alert1.showAndWait();
			e.printStackTrace();
		}
	}
	
	@FXML
	public void mostrarOpcionesCuenta(MouseEvent event) {
		opcionesMiCuenta.setVisible(true);
		FadeTransition fade=new FadeTransition();
		fade.setNode(opcionesMiCuenta);
		fade.setDuration(Duration.millis(300));
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
	}
	
	@FXML
	public void ocultarOpcionesCuenta(MouseEvent event) {
		opcionesMiCuenta.setVisible(false); 
	}

	@FXML
	public void mostrarNotificaciones(MouseEvent event){
		notificaciones.setVisible(true);
		FadeTransition fade=new FadeTransition();
		fade.setNode(notificaciones);
		fade.setDuration(Duration.millis(300));
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
	}
	
	@FXML
	public void ocultarNotificaciones(MouseEvent event){
		notificaciones.setVisible(false); 
	}
	
	@FXML
	public void cerrar(MouseEvent event) {
		Platform.exit();
	}
}
