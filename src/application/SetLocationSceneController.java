package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import connection.Queries;
import dataClass.Departamento;
import dataClass.Municipio;
import dataClass.Pais;
import dataClass.Sesion;
import dataClass.Ubicacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SetLocationSceneController implements Initializable{
	
	@FXML
	private ComboBox<Pais> cboxPais;
	
	@FXML
    private ComboBox<Departamento> cboxDepto;

    @FXML
    private ComboBox<Municipio> cboxMunicipio;
    
    @FXML
    private Button agregar;

    @FXML
    private Label ubi;
    
    private List<Pais> paises = new ArrayList<>();
    private List<Departamento> deptos = new ArrayList<>();
    private List<Municipio> municipios = new ArrayList<>();
    
    private List<Departamento> filteredDeptos = new ArrayList<>();
    private List<Municipio> filteredMunicipios = new ArrayList<>();
    
    private ObservableList<Pais> obsPaises = FXCollections.observableArrayList();
    private ObservableList<Departamento> obsDeptos = FXCollections.observableArrayList();
    private ObservableList<Municipio> obsMunicipios = FXCollections.observableArrayList();
    
    private String result;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cboxPais.setItems(obsPaises);
		cboxDepto.setItems(obsDeptos);
		cboxMunicipio.setItems(obsMunicipios);
		ubi.setTextAlignment(TextAlignment.CENTER);
	}
	
	@FXML
    public void openAndFilterPaises(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			cboxPais.hide();
			if(!obsPaises.isEmpty()) {
				cboxPais.setValue(obsPaises.get(0));
			}
		}else {
			obsPaises.clear();
			String filter = cboxPais.getEditor().getText();
			paises.stream().forEach(pais -> {
				if(pais.getNombre().toLowerCase().startsWith(filter.toLowerCase())) {
					obsPaises.add(pais);
				}
			});
			if (cboxPais.getValue()!=null) {				
				cboxPais.setValue(null);
				cboxDepto.setValue(null);
				cboxMunicipio.setValue(null);
			}
			cboxPais.show();
		}
    }
	
	@FXML
    void openAndFilterDeptos(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			cboxDepto.hide();
			if(!obsDeptos.isEmpty()) {
				cboxDepto.setValue(obsDeptos.get(0));
			}
		}else {
			obsDeptos.clear();
			String filter = cboxDepto.getEditor().getText();
			filteredDeptos.stream().forEach(depto -> {
				if(depto.getNombre().toLowerCase().startsWith(filter.toLowerCase())) {
					obsDeptos.add(depto);
				}
			});
			if(cboxDepto.getValue()!=null) {				
				cboxDepto.setValue(null);
				cboxMunicipio.setValue(null);
			}
			cboxDepto.show();
		}
    }
	
	@FXML
    void openAndFilterMunicipios(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			cboxMunicipio.hide();
			if(!obsMunicipios.isEmpty()) {
				cboxMunicipio.setValue(obsMunicipios.get(0));
			}
		}else {
			obsMunicipios.clear();
			String filter = cboxMunicipio.getEditor().getText();
			filteredMunicipios.stream().forEach(municipio -> {
				if(municipio.getNombre().toLowerCase().startsWith(filter.toLowerCase())) {
					obsMunicipios.add(municipio);
				}
			});
			if(cboxMunicipio.getValue()!=null) {				
				cboxMunicipio.setValue(null);
			}
			cboxMunicipio.show();
		}
    }
	
	@FXML
	void paisSelected(ActionEvent event) {
		if(cboxPais.getValue()!=null) {
			cboxDepto.setValue(null);
			cboxMunicipio.setValue(null);
		}
	}
	
	@FXML
    void deptoSelected(ActionEvent event) {
		if(cboxDepto.getValue()!=null) {
			try { 
				Departamento depto = cboxDepto.getValue();
				cboxPais.setValue(depto.getPais());
				cboxMunicipio.setValue(null);
				cboxDepto.setValue(depto);
			}catch(java.lang.ClassCastException e) {}
		}
    }

    @FXML
    void municipioSelected(ActionEvent event) {
    	if(cboxMunicipio.getValue()!=null) {
    		try {    			
    			Municipio muni = cboxMunicipio.getValue();
    			cboxDepto.setValue(muni.getDepartamento());
    			cboxPais.setValue(muni.getDepartamento().getPais());
    			cboxMunicipio.setValue(muni);
    		}catch(java.lang.ClassCastException e) {}
    	}
    }
    
    @FXML
    void agregarUbicacion(MouseEvent event) {
    	if (!(cboxPais.getValue()==null) && !(cboxDepto.getValue()==null) && !(cboxMunicipio.getValue()==null)) {
    		result = cboxPais.getValue()+","+cboxDepto.getValue()+","+cboxMunicipio.getValue();
    		Stage stage = (Stage) this.agregar.getScene().getWindow();
    		stage.close();
    	}else {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe llenar todo los campos");
            alert.showAndWait();
    	}
    }
    
	public void updateLocations(Map<Integer, Ubicacion> ubicaciones) {
		ubicaciones.forEach((i,u)->{			
			if (!paises.contains(u.getPais())) {
				paises.add(u.getPais());
				obsPaises.add(u.getPais());
			}
			if (!deptos.contains(u.getDepartamento())) {
				deptos.add(u.getDepartamento());
				filteredDeptos.add(u.getDepartamento());
				obsDeptos.add(u.getDepartamento());
			}
			if (!municipios.contains(u.getMunicipio())) {
				municipios.add(u.getMunicipio());
				filteredMunicipios.add(u.getMunicipio());
				obsMunicipios.add(u.getMunicipio());
			}
		});
	}
	
	public String getResult() {
		return result;
	}
}