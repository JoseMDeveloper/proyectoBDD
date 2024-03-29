package application;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.filechooser.FileView;

import connection.Queries;
import dataClass.Departamento;
import dataClass.Municipio;
import dataClass.Pais;
import dataClass.Sesion;
import dataClass.Ubicacion;
import dataClass.Usuario;
import dataClass.Vivienda;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
//import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ClientSceneController extends PrincipalAbstractController implements Initializable{
	@FXML
    private TextField ArriendoMax;

    @FXML
    private TextField ArriendoMin;

    @FXML
    private Button btnSearch;

    @FXML
    private Button nuevaUbicacion;
    
    @FXML
    private ComboBox<String> cmbDeptos;

    @FXML
    private ComboBox<String> cmbMunicipios;

    @FXML
    private ComboBox<String> cmbPaises;

    @FXML
    private Spinner<Integer> selectNumRooms;

    @FXML
    private ComboBox<String> selectTipoPropiedad;
    
    @FXML
    private ImageView cerrar;
    
//  @FXML
//  private Button btnGuardados;
    
    @FXML
    private Usuario user;
    
    private List<String> paises = new ArrayList<>();
    private List<String> deptos = new ArrayList<>();
    private List<String> municipios = new ArrayList<>();
    
    private List<String> ubis = new ArrayList<>();
    private Map<Integer, Ubicacion> ubicaciones;
    
    @FXML
    private Button btnCerrarSesion;
	
	@Override
 	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		SpinnerValueFactory <Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50);
		valueFactory.setValue(0);
		selectNumRooms.setValueFactory(valueFactory);
		String[] options = {null,"Casa","Apartamento"};
		selectTipoPropiedad.getItems().addAll(options);
		cmbPaises.getItems().add("Pais");
		cmbDeptos.getItems().add("Depto");
		cmbMunicipios.getItems().add("Municipio");
		try {
			updateLocations();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setUserLocation();
	}
	
	@FXML
	public void search(MouseEvent event) throws IOException, NumberFormatException, ClassNotFoundException, SQLException {
		if(!ubis.isEmpty()) {			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/source/SearchScene2.fxml"));
			Parent root = loader.load();
			
			SearchScene2Controller controlador = loader.getController();
			Integer arrMin = null;
			Integer arrMax = null;
			
			if(!ArriendoMin.getText().isBlank()) {
				try {					
					arrMin = Integer.parseInt(ArriendoMin.getText());
				}catch(NumberFormatException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setHeaderText(null);
		            alert.setTitle("Error");
		            alert.setContentText("El valor de arriendo minimo ingresado no es un numero\n"
		            		+ "Se conciderara como nulo");
		            alert.showAndWait();
				}
			}
			if (!ArriendoMax.getText().isBlank()) {
				try {					
					arrMax = Integer.parseInt(ArriendoMax.getText());
				}catch(NumberFormatException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setHeaderText(null);
		            alert.setTitle("Error");
		            alert.setContentText("El valor de arriendo maximo ingresado no es un numero\n"
		            		+ "Se conciderara como nulo");
		            alert.showAndWait();
				}
			}
			
			List<Vivienda> viviendas = Queries.buscarPropiedades(paises, deptos, municipios, selectTipoPropiedad.getValue(), selectNumRooms.getValue(),
					arrMin, arrMax);
			controlador.setLocations(paises, deptos, municipios);
			controlador.search(viviendas);
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			Stage thisStage = (Stage) this.btnSearch.getScene().getWindow();
			thisStage.close();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar una ubicacion");
            alert.showAndWait();
		}
	}
	
	@FXML
	public void addLocation(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/source/AddLocationScene.fxml"));
        Parent root = loader.load();
        
        AddLocationSceneController controlador = loader.getController();
//        controlador.initAttributtes(personas);
        
        controlador.updateLocations(ubicaciones);
        controlador.setSelectedUbicaciones(ubis);
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        clearUbicaciones();
        ubis.addAll(controlador.getResult());
        
        ubis.forEach(u -> {
        	String[] us = u.split(",");
        	cmbPaises.getItems().add(us[0]);
        	paises.add(us[0]);
        	if(us.length>1) {
        		cmbDeptos.getItems().add(us[1]);
        		deptos.add(us[1]);
        		if(us.length==3) {
        			cmbMunicipios.getItems().add(us[2]);
        			municipios.add(us[2]);        			
        		}else {
        			cmbMunicipios.getItems().add("sin filtro");
            		municipios.add("sin filtro");
            	}
        	}else {
        		cmbDeptos.getItems().add("sin filtro");
        		deptos.add("sin filtro");
        		cmbMunicipios.getItems().add("sin filtro");
        		municipios.add("sin filtro");
        	}
        });
	}
	
	@FXML
    void cancelSelection(ActionEvent event) {
		cmbPaises.setValue("Pais");
		cmbDeptos.setValue("Depto");
		cmbMunicipios.setValue("Municipio");
    }
	
	@FXML
	public void sidebar(MouseEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/source/sidebar.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
	}
	
//	public void misVisitas(MouseEvent event) throws IOException {
//		Parent root = FXMLLoader.load(getClass().getResource("/source/ProfileScene.fxml"));
//		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.centerOnScreen();
//	}
	
	public void setUserLocation() {
		if(Sesion.getUser().getIDubicacion()!=0) {
			paises.add(ubicaciones.get(Sesion.getUser().getIDubicacion()).getPais().toString());
			cmbPaises.getItems().add(ubicaciones.get(Sesion.getUser().getIDubicacion()).getPais().toString());
			
			deptos.add(ubicaciones.get(Sesion.getUser().getIDubicacion()).getDepartamento().toString());
			cmbDeptos.getItems().add(ubicaciones.get(Sesion.getUser().getIDubicacion()).getDepartamento().toString());
			
			municipios.add(ubicaciones.get(Sesion.getUser().getIDubicacion()).getMunicipio().toString());
			cmbMunicipios.getItems().add(ubicaciones.get(Sesion.getUser().getIDubicacion()).getMunicipio().toString());
		
			ubis.add(ubicaciones.get(Sesion.getUser().getIDubicacion()).getPais()+","
					+ubicaciones.get(Sesion.getUser().getIDubicacion()).getDepartamento()+","
					+ubicaciones.get(Sesion.getUser().getIDubicacion()).getMunicipio());
		}
	}
	
	public void updateLocations() throws ClassNotFoundException, SQLException {
		this.ubicaciones = Queries.obtenerUbicacion();
	}
	
	public void clearUbicaciones() {
		ubis.clear();
		paises.clear();
		cmbPaises.getItems().clear();
		cmbPaises.getItems().add("Pais");
		cmbPaises.valueProperty().set(null);
		
		deptos.clear();
		cmbDeptos.getItems().clear();
		cmbDeptos.getItems().add("Depto");
		cmbDeptos.valueProperty().set(null);
		
		municipios.clear();
		cmbMunicipios.getItems().clear();
		cmbMunicipios.getItems().add("Municipio");
		cmbMunicipios.valueProperty().set(null);
	}
}
