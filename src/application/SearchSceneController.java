package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import connection.Queries;
import dataClass.Vivienda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SearchSceneController implements Initializable{
	@FXML private TextField Buscador;
	@FXML private TextField ArriendoMin;
	@FXML private TextField ArriendoMax;
	@FXML private Button BotonHabitaciones;
	@FXML private MenuButton TipoPropiedad;
	@FXML private Button BotonBuscar;
	@FXML private Spinner<Integer> selectNumRooms;
	@FXML private TableView<Vivienda> tblViviendas;
	@FXML private TableColumn<Vivienda, Integer> colID;
	@FXML private TableColumn<Vivienda, String> colDireccion;
	@FXML private TableColumn<Vivienda, Integer> colHabitaciones;
	@FXML private TableColumn<Vivienda, String> colTipoPropiedad;
	@FXML private TableColumn<Vivienda, Float> colRentaMensual;
	@FXML private TableColumn<Vivienda, String> colFecha;
	@FXML private TableColumn<Vivienda, String> colDescripcion;
	@FXML private TableColumn<Vivienda, String> colPais;
	
	private ObservableList<Vivienda> viviendas;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		viviendas = FXCollections.observableArrayList();
				
		colID.setCellValueFactory(new PropertyValueFactory<Vivienda, Integer>("id"));
		colDireccion.setCellValueFactory(new PropertyValueFactory<Vivienda, String>("direccion"));
		colHabitaciones.setCellValueFactory(new PropertyValueFactory<Vivienda, Integer>("cantHabitaciones"));
		colTipoPropiedad.setCellValueFactory(new PropertyValueFactory<Vivienda, String>("tipo"));
		colRentaMensual.setCellValueFactory(new PropertyValueFactory<Vivienda, Float>("precioRentaMensual"));
		colFecha.setCellValueFactory(new PropertyValueFactory<Vivienda, String>("fecha"));
		colDescripcion.setCellValueFactory(new PropertyValueFactory<Vivienda, String>("descripcion"));
		colPais.setCellValueFactory(new PropertyValueFactory<Vivienda, String>("pais"));
		tblViviendas.setItems(viviendas);
		
		SpinnerValueFactory <Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
		valueFactory.setValue(1);
		selectNumRooms.setValueFactory(valueFactory);
		tblViviendas.setItems(viviendas);
	}
	
	public void noSelection(ActionEvent event) {
		TipoPropiedad.setText("Tipo de Propiedad");
	}
	
	public void selectCasa(ActionEvent event) {
		TipoPropiedad.setText("Casa");
	}
	
	public void selectApto(ActionEvent event) {
		TipoPropiedad.setText("Apartamento");
	}
	
	public void search(MouseEvent event) throws SQLException, ClassNotFoundException {
		viviendas.clear();
		List<String> paises = new ArrayList<>();
		paises.add("Colombia");
		viviendas.addAll(Queries.buscarPropiedades(paises, null, null, null, null, null, null));
		tblViviendas.refresh();
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
