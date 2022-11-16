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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	
	@Override
 	public void initialize(URL arg0, ResourceBundle arg1) {
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
}
