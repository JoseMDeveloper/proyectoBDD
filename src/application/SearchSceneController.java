package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import connection.Queries;
import dataClass.Vivienda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SearchSceneController implements Initializable{
	
	@FXML
    private Button aceptar;
	
	@FXML
	private TableView<Vivienda> tblViviendas;
	
	@FXML
	private TableColumn<Vivienda, Integer> colID;
	
	@FXML
	private TableColumn<Vivienda, String> colDireccion;
	
	@FXML
	private TableColumn<Vivienda, Integer> colHabitaciones;
	
	@FXML
	private TableColumn<Vivienda, String> colTipoPropiedad;
	
	@FXML
	private TableColumn<Vivienda, Float> colRentaMensual;
	
	@FXML
	private TableColumn<Vivienda, String> colFecha;
	
	@FXML
	private TableColumn<Vivienda, String> colDescripcion;
	
	@FXML
	private TableColumn<Vivienda, String> colPais;
	
	private ObservableList<Vivienda> viviendas = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colID.setCellValueFactory(new PropertyValueFactory<Vivienda, Integer>("id"));
		colDireccion.setCellValueFactory(new PropertyValueFactory<Vivienda, String>("direccion"));
		colHabitaciones.setCellValueFactory(new PropertyValueFactory<Vivienda, Integer>("cantHabitaciones"));
		colTipoPropiedad.setCellValueFactory(new PropertyValueFactory<Vivienda, String>("tipo"));
		colRentaMensual.setCellValueFactory(new PropertyValueFactory<Vivienda, Float>("precioRentaMensual"));
		colFecha.setCellValueFactory(new PropertyValueFactory<Vivienda, String>("fecha"));
		colDescripcion.setCellValueFactory(new PropertyValueFactory<Vivienda, String>("descripcion"));
		colPais.setCellValueFactory(new PropertyValueFactory<Vivienda, String>("pais"));
		tblViviendas.setItems(viviendas);
	}
	
	@FXML
    void aceptarConsultas(MouseEvent event) {
		Stage stage = (Stage) this.aceptar.getScene().getWindow();
        stage.close();
    }
	
	public void search(List<Vivienda> vivs) throws SQLException, ClassNotFoundException {
		viviendas.addAll(vivs);
		tblViviendas.setItems(viviendas);
	}
}
