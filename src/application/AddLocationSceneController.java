package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import connection.Queries;
import dataClass.Departamento;
import dataClass.Municipio;
import dataClass.Pais;
import dataClass.Ubicacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AddLocationSceneController implements Initializable{
	
	@FXML
	private ComboBox<Pais> cboxPais;
	
	@FXML
    private ComboBox<Departamento> cboxDepto;

    @FXML
    private ComboBox<Municipio> cboxMunicipio;
    
    private List<Pais> paises = new ArrayList<>();
    private List<Departamento> deptos = new ArrayList<>();
    private List<Municipio> municipios = new ArrayList<>();
    
    private List<Departamento> filteredDeptos = new ArrayList<>();
    private List<Municipio> filteredMunicipios = new ArrayList<>();
    
    private ObservableList<Pais> obsPaises = FXCollections.observableArrayList();
    private ObservableList<Departamento> obsDeptos = FXCollections.observableArrayList();
    private ObservableList<Municipio> obsMunicipios = FXCollections.observableArrayList();
    
    private List<Ubicacion> ubicaciones;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			updateLocations();
			cboxPais.setItems(obsPaises);
			cboxDepto.setItems(obsDeptos);
			cboxMunicipio.setItems(obsMunicipios);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    void openAndFilterPaises(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			cboxPais.hide();
			if(!obsPaises.isEmpty()) {
				cboxPais.setValue(obsPaises.get(0));
				filteredDeptos.clear();
				filteredDeptos.addAll(obsPaises.get(0).getDepartamentos());
			}
		}else {
			obsPaises.clear();
			String filter = cboxPais.getEditor().getText();
			paises.stream().forEach(pais -> {
				if(pais.getNombre().toLowerCase().startsWith(filter.toLowerCase())) {
					obsPaises.add(pais);
				}
			});
			cboxPais.show();
		}
    }
	
	@FXML
    void openAndFilterDeptos(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			cboxDepto.hide();
			if(!obsDeptos.isEmpty()) {
				cboxDepto.setValue(obsDeptos.get(0));
				cboxPais.setValue(obsDeptos.get(0).getPais());
			}
		}else {
			obsDeptos.clear();
			String filter = cboxDepto.getEditor().getText();
			deptos.stream().forEach(depto -> {
				if(depto.getNombre().toLowerCase().startsWith(filter.toLowerCase())) {
					obsDeptos.add(depto);
				}
			});
			cboxDepto.show();
		}
    }
	
	@FXML
    void openAndFilterMunicipios(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			cboxMunicipio.hide();
			if(!obsMunicipios.isEmpty()) {
				cboxMunicipio.setValue(obsMunicipios.get(0));
				cboxDepto.setValue(obsMunicipios.get(0).getDepartamento());
				cboxPais.setValue(obsMunicipios.get(0).getDepartamento().getPais());
			}
		}else {
			obsMunicipios.clear();
			String filter = cboxMunicipio.getEditor().getText();
			municipios.stream().forEach(municipio -> {
				if(municipio.getNombre().toLowerCase().startsWith(filter.toLowerCase())) {
					obsMunicipios.add(municipio);
				}
			});
			cboxMunicipio.show();
		}
    }
	
	public void updateLocations() throws ClassNotFoundException, SQLException {
		this.ubicaciones = Queries.obtenerUbicacion();
		for(Ubicacion ubicacion: ubicaciones) {
			if (!paises.contains(ubicacion.getPais())) {
				paises.add(ubicacion.getPais());
				obsPaises.add(ubicacion.getPais());
			}
			if (!deptos.contains(ubicacion.getDepartamento())) {
				deptos.add(ubicacion.getDepartamento());
				obsDeptos.add(ubicacion.getDepartamento());
			}
			if (!municipios.contains(ubicacion.getMunicipio())) {
				municipios.add(ubicacion.getMunicipio());
				obsMunicipios.add(ubicacion.getMunicipio());
			}
		}
	}
}
