package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import connection.Queries;
import dataClass.Sesion;
import dataClass.Visita;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistorialController implements Initializable {
	@FXML
	private TableView <Visita>tabla;
	@FXML
	private TableColumn<Visita, Integer> IDvivienda;
	@FXML
	private TableColumn<Visita, String> fecha;
	
	private List <Visita> visitas=new ArrayList<>();

	private ObservableList<Visita> tablita = FXCollections.observableArrayList();  
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
			visitas=Queries.historialVisitasCliente(Sesion.getUser().getId());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	tablita.addAll(visitas);
    	IDvivienda.setCellValueFactory(new PropertyValueFactory<Visita,Integer>("IDvivienda"));
    	fecha.setCellValueFactory(new PropertyValueFactory<Visita,String>("fecha"));
    	tabla.setItems(tablita);
    	tabla.refresh();
    }
}
