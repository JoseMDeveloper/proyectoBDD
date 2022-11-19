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
    @FXML
    private Button visitas;
    
//    @FXML
//    private Button btnCerrarSesion;
//    
//    @FXML
//    private Button btnGuardados;
//    
//	@FXML
//	private AnchorPane anchorpanecuenta;
    
    @FXML
    private Usuario user;
    
    private List<String> paises = new ArrayList<>();
    private List<String> deptos = new ArrayList<>();
    private List<String> municipios = new ArrayList<>();
    
    private List<String> ubis = new ArrayList<>();
    private Map<Integer, Ubicacion> ubicaciones;
    
    @FXML
    private Button btnCerrarSesion;
     
	@FXML
	private AnchorPane anchorpanecuenta;
	
	@FXML
	private AnchorPane anchorpanenoti;
	
	@Override
 	public void initialize(URL arg0, ResourceBundle arg1) {
		anchorpanecuenta.setVisible(false);
		anchorpanenoti.setVisible(false);
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
	
	public void search() throws IOException, NumberFormatException, ClassNotFoundException, SQLException {
		if(!ubis.isEmpty()) {			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/source/SearchScene.fxml"));
			Parent root = loader.load();
			
			SearchSceneController controlador = loader.getController();
			
			Integer arrMin = null;
			Integer arrMax = null;
			
			if(!ArriendoMin.getText().isBlank()) {
				arrMin = Integer.parseInt(ArriendoMin.getText());
			}
			if (!ArriendoMax.getText().isBlank()) {
				arrMax = Integer.parseInt(ArriendoMax.getText());
			}
			
			List<Vivienda> viviendas = Queries.buscarPropiedades(paises, deptos, municipios, selectTipoPropiedad.getValue(), selectNumRooms.getValue(),
					arrMin, arrMax);
			controlador.search(viviendas);
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una ubicacion");
            alert.showAndWait();
		}
	}
	
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
        		}
        	}
        });
	}
	
	@FXML
    void cancelSelection(ActionEvent event) {
		cmbPaises.setValue("Pais");
		cmbDeptos.setValue("Depto");
		cmbMunicipios.setValue("Municipio");
    }
	
	public void sidebar(MouseEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/source/sidebar.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
	}
	

	public void mostrarOpcionesCuenta(MouseEvent event){
		anchorpanecuenta.setVisible(true);
		FadeTransition fade=new FadeTransition();
		fade.setNode(anchorpanecuenta);
		fade.setDuration(Duration.millis(300));
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
	}
	
	public void quitarOpcionesCuenta(MouseEvent event){
		
		anchorpanecuenta.setVisible(false); 
		
	}
	
	public void mostrarnoti(MouseEvent event){
		
		anchorpanenoti.setVisible(true);
		FadeTransition fade=new FadeTransition();
		fade.setNode(anchorpanenoti);
		fade.setDuration(Duration.millis(300));
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
		
	}
	
	public void quitarnoti(MouseEvent event){
		
		anchorpanenoti.setVisible(false); 
		
	}

	public void cerrarSesion(MouseEvent event){
		
		Platform.exit();
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
	public void cerrar(MouseEvent event) {
		Platform.exit();
	}
}
