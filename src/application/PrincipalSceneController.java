package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
//import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PrincipalSceneController implements Initializable{
	
	@FXML
    private TextField ArriendoMax;

    @FXML
    private TextField ArriendoMin;

    @FXML
    private Button btnMiCuenta;

    @FXML
    private Button btnNotificaciones;

    @FXML
    private Button btnSearch;

    @FXML
    private Button nuevaUbicacion;

    @FXML
    private Spinner<Integer> selectNumRooms;

    @FXML
    private ComboBox<?> selectTipoPropiedad;
    
    @FXML
    private Button btnCerrarSesion;
     
	@FXML
	private AnchorPane anchorpanecuenta;
	@FXML
	private AnchorPane anchorpanenoti;
	
	@Override
 	public void initialize(URL arg0, ResourceBundle arg1) {
		anchorpanecuenta.setVisible(false);
		anchorpanenoti.setVisible(false);
		SpinnerValueFactory <Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
		valueFactory.setValue(1);
		selectNumRooms.setValueFactory(valueFactory);
	}
	
	public void search() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/source/SearchScene.fxml"));
        Parent root = loader.load();
        
        SearchSceneController controlador = loader.getController();
//        controlador.initAttributtes(personas);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
	}
	
	public void addLocation(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/source/AddLocationScene.fxml"));
        Parent root = loader.load();
        
        AddLocationSceneController controlador = loader.getController();
//        controlador.initAttributtes(personas);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
	}
	
	public void sidebar(MouseEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/source/sidebar.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
	}

	public void mostrarOpcionesCuenta(MouseEvent event)
	{
		anchorpanecuenta.setVisible(true);
		FadeTransition fade=new FadeTransition();
		fade.setNode(anchorpanecuenta);
		fade.setDuration(Duration.millis(300));
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
	}
	public void quitarOpcionesCuenta(MouseEvent event)
	{
		
		anchorpanecuenta.setVisible(false); 
		
	}
	public void mostrarnoti(MouseEvent event)
	{
		
		anchorpanenoti.setVisible(true);
		FadeTransition fade=new FadeTransition();
		fade.setNode(anchorpanenoti);
		fade.setDuration(Duration.millis(300));
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
		
	}
	public void quitarnoti(MouseEvent event)
	{
		
		anchorpanenoti.setVisible(false); 
		
	}

	public void cerrarSesion(MouseEvent event)
	{
		
		Platform.exit();
	}
//	public void misVisitas(MouseEvent event) throws IOException {
//		Parent root = FXMLLoader.load(getClass().getResource("/source/ProfileScene.fxml"));
//		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.centerOnScreen();
//	}
}
