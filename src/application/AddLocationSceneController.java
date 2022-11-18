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
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddLocationSceneController implements Initializable{
	
	@FXML
	private ComboBox<Pais> cboxPais;
	
	@FXML
    private ComboBox<Departamento> cboxDepto;

    @FXML
    private ComboBox<Municipio> cboxMunicipio;
    
    @FXML
    private Button agregar;

    @FXML
    private Button borrar;
    
    @FXML
    private Button aceptar;
    
    @FXML
    private ListView<String> lista;
    
    private List<Pais> paises = new ArrayList<>();
    private List<Departamento> deptos = new ArrayList<>();
    private List<Municipio> municipios = new ArrayList<>();
    
    private List<Departamento> filteredDeptos = new ArrayList<>();
    private List<Municipio> filteredMunicipios = new ArrayList<>();
    
    private ObservableList<Pais> obsPaises = FXCollections.observableArrayList();
    private ObservableList<Departamento> obsDeptos = FXCollections.observableArrayList();
    private ObservableList<Municipio> obsMunicipios = FXCollections.observableArrayList();
    
    private List<String> result = new ArrayList<>();
    
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
			Departamento depto = cboxDepto.getValue();
			cboxPais.setValue(depto.getPais());
			cboxMunicipio.setValue(null);
			cboxDepto.setValue(depto);
		}
    }

    @FXML
    void municipioSelected(ActionEvent event) {
    	if(cboxMunicipio.getValue()!=null) {    		
    		Municipio muni = cboxMunicipio.getValue();
    		cboxDepto.setValue(muni.getDepartamento());
    		cboxPais.setValue(muni.getDepartamento().getPais());
    		cboxMunicipio.setValue(muni);
    	}
    }
    
    @FXML
    void agregarUbicacion(MouseEvent event) {
    	if (cboxPais.getValue()!=null) {   		
    		String ubi = "Pais: "+cboxPais.getValue();
    		if(cboxDepto.getValue()!=null) {
    			ubi += " | Departamento: "+ cboxDepto.getValue();
    			if(cboxMunicipio.getValue()!=null) {
    				ubi += " | Municipio: "+ cboxMunicipio.getValue();
    			}
    		}
    		lista.getItems().add(ubi);
    		result.add(cboxPais.getValue()+","+cboxDepto.getValue()+","+cboxMunicipio.getValue());
    	}
    }

	@FXML
    void borrarUbicacion(MouseEvent event) {
    	String ubi = lista.getSelectionModel().getSelectedItem();
    	if (ubi == null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una ubicacion");
            alert.showAndWait();
    	}else {
    		lista.getItems().remove(ubi);
    	}
    }
    
    @FXML
    void aceptarUbicaciones(MouseEvent event) {
    	Stage stage = (Stage) this.aceptar.getScene().getWindow();
        stage.close();
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
				filteredDeptos.add(ubicacion.getDepartamento());
				obsDeptos.add(ubicacion.getDepartamento());
			}
			if (!municipios.contains(ubicacion.getMunicipio())) {
				municipios.add(ubicacion.getMunicipio());
				filteredMunicipios.add(ubicacion.getMunicipio());
				obsMunicipios.add(ubicacion.getMunicipio());
			}
		}
	}
	
	public List<String> getResult() {
		return result;
	}
	
	public void setUserLocation() {
		String ubi = "Pais: "+ubicaciones.get(Sesion.getUser().getIDubicacion()).getPais();
		if(cboxDepto.getValue()!=null) {
			ubi += " | Departamento: "+ubicaciones.get(Sesion.getUser().getIDubicacion()).getDepartamento();
			if(cboxMunicipio.getValue()!=null) {
				ubi += " | Municipio: "+ubicaciones.get(Sesion.getUser().getIDubicacion()).getMunicipio();
			}
		}
		lista.getItems().add(ubi);
		result.add(cboxPais.getValue()+","+cboxDepto.getValue()+","+cboxMunicipio.getValue());
	}
}
