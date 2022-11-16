package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import connection.Queries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;

public class AddLocationSceneController implements Initializable{
	
	@FXML
	private ComboBox<String> cboxPais;
	
	@FXML
    private ComboBox<String> cboxDepto;

    @FXML
    private ComboBox<String> cboxMunicipio;
    
    private List<String> paises = new ArrayList<>();
    private List<String> deptos = new ArrayList<>();
    private List<String> municipios = new ArrayList<>();
    
    private ObservableList<String> obsPaises = FXCollections.observableArrayList();
    private ObservableList<String> obsDeptos = FXCollections.observableArrayList();
    private ObservableList<String> obsMunicipios = FXCollections.observableArrayList();
    
    private List<String[]> ubicaciones;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			updateLocations();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    void openAndFilterPaises(KeyEvent event) {
		obsPaises.clear();
		String filter = cboxPais.getEditor().getText();
		for(String pais: paises) {
			if(pais.toLowerCase().startsWith(filter.toLowerCase())) {
				obsPaises.add(pais);
			}
		}
		cboxPais.show();
    }
	
	@FXML
    void openAndFilterDeptos(KeyEvent event) {
		obsDeptos.clear();
		String filter = cboxDepto.getEditor().getText();
		for(String depto: deptos) {
			if(depto.toLowerCase().startsWith(filter.toLowerCase())) {
				obsDeptos.add(depto);
			}
		}
		cboxDepto.show();
    }
	
	@FXML
    void openAndFilterMunicipios(KeyEvent event) {
		obsMunicipios.clear();
		String filter = cboxMunicipio.getEditor().getText();
		for(String municipio: municipios) {
			if(municipio.toLowerCase().startsWith(filter.toLowerCase())) {
				obsMunicipios.add(municipio);
			}
		}
		cboxMunicipio.show();
    }
	
	public void updateLocations() throws ClassNotFoundException, SQLException {
		this.ubicaciones = Queries.obtenerUbicacion();
		for(String[] ubicacion: ubicaciones) {
			if (!paises.contains(ubicacion[0])) {
				paises.add(ubicacion[0]);
				obsPaises.add(ubicacion[0]);
			}
			if (!deptos.contains(ubicacion[1])) {
				deptos.add(ubicacion[1]);
				obsDeptos.add(ubicacion[1]);
			}
			if (!municipios.contains(ubicacion[2])) {
				municipios.add(ubicacion[2]);
				obsMunicipios.add(ubicacion[2]);
			}
		}
		cboxPais.setItems(obsPaises);
		cboxDepto.setItems(obsDeptos);
		cboxMunicipio.setItems(obsMunicipios);
	}
}
