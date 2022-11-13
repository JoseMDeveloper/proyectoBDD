package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.MenuButton;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class SearchSceneController implements Initializable{
	@FXML private TextField Buscador;
	@FXML private TextField ArriendoMin;
	@FXML private TextField ArriendoMax;
	@FXML private Button BotonHabitaciones;
	@FXML private MenuButton TipoPropiedad;
	@FXML private Button BotonBuscar;
	@FXML private Spinner<Integer> selectNumRooms1;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SpinnerValueFactory <Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
		valueFactory.setValue(1);
		
		selectNumRooms1.setValueFactory(valueFactory);
	}
	
	
}
