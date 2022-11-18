package application;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import connection.Queries;
import dataClass.Departamento;
import dataClass.Municipio;
import dataClass.Pais;
import dataClass.Usuario;
import dataClass.Vivienda;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
//import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
	
	@Override
 	public void initialize(URL arg0, ResourceBundle arg1) {
//		anchorpanecuenta.setVisible(false);
		SpinnerValueFactory <Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
		valueFactory.setValue(1);
		selectNumRooms.setValueFactory(valueFactory);
		String[] options = {"Casa","Apartamento"};
		selectTipoPropiedad.getItems().addAll(options);
	}
	
	public void search() throws IOException, NumberFormatException, ClassNotFoundException, SQLException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/source/SearchScene.fxml"));
        Parent root = loader.load();
        
        SearchSceneController controlador = loader.getController();
//        controlador.initAttributtes(personas);
        
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
        
        List<String> ubicaciones = controlador.getResult();
        
        ubicaciones.forEach(u -> {
        	String[] us = u.split(",");
        	cmbPaises.getItems().add(us[0]);
        	paises.add(us[0]);
        	cmbDeptos.getItems().add(us[1]);
        	deptos.add(us[1]);
        	cmbMunicipios.getItems().add(us[2]);
        	municipios.add(us[2]);
        });
	}
	
//	public void mostrarOpcionesCuenta(MouseEvent event)
//	{
//	
//		anchorpanecuenta.setVisible(true);
//		FadeTransition fade=new FadeTransition();
//		fade.setNode(anchorpanecuenta);
//		fade.setDuration(Duration.millis(300));
//		fade.setFromValue(0);
//		fade.setToValue(1);
//		fade.play();
//	}
//	public void perfil(MouseEvent event) throws IOException
//	{
//		Parent root = FXMLLoader.load(getClass().getResource("/source/ProfileScene.fxml"));
//		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.centerOnScreen();
//	}
	
	
	//hay un error, que si te sales por los lados del boton no se quita, y es porque el anchorpane no llega hasta alla arriba y si lo hace tapa el boton, 
	//y si se le pone el comando de salir al boton apenas se intenta seleccinar una opcion se quita
//	public void quitarOpcionesCuenta(MouseEvent event)
//	{
//		
//		anchorpanecuenta.setVisible(false);
//	}

//	public void cerrarSesion(MouseEvent event)
//	{
//		
//		Platform.exit();
//	}
	//ehhh si encontramos la manera de hacer que algo ocurra cada cierto tiempo creo que puedo hacer que se haga una transicion de imagenes del fondo
//	public void transionImagenes(Node nodo)
//	{
//		TranslateTransition imagenes=new TranslateTransition();
//		imagenes.setNode(nodo);
//		imagenes.setDuration(Duration.seconds(5));	
//	}
}
