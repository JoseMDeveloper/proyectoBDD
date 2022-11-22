package application;

import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import connection.Queries;
import dataClass.Sesion;
import dataClass.Ubicacion;
import dataClass.Vivienda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class PropiedadDuenoController extends PrincipalAbstractController implements Initializable{
	@FXML
	private VBox chosenFruitCard;
	@FXML
	private Label fruitNameLable;
	@FXML
	private Label fruitPriceLabel;
	@FXML
	private ImageView fruitImg;
	@FXML
	private ScrollPane scroll;
	@FXML
	private GridPane grid;
	@FXML
	private ImageView cerrar;
	@FXML
	private ImageView inicio;
	@FXML
    private Button habitaciones;
	@FXML
    private Label numPropiedades;
	@FXML
	private Label cuenta;
	@FXML
    private Button masHabitaciones;
    @FXML
    private Button menosHabitaciones;
    @FXML
    private ComboBox<String> cmbDeptos;
    @FXML
    private ComboBox<String> cmbMunicipios;
    @FXML
    private ComboBox<String> cmbPaises;
    @FXML
    private TextField ArriendoMax;
    @FXML
    private TextField ArriendoMin;
    @FXML
    private Button btnSearch;
    @FXML
    private Pane btnApto;
    @FXML
    private Pane btnCasa;
    @FXML
    private Button clearSelect;
    @FXML
    private Button nuevaUbicacion;
    
    private Integer cantHabs=0;
    private String tipoVivienda=null;
    
    private List<String> paises = new ArrayList<>();
    private List<String> deptos = new ArrayList<>();
    private List<String> municipios = new ArrayList<>();
    
    private List<String> ubis = new ArrayList<>();
	private List<Vivienda> viviendas=new ArrayList<>();
	private Map<Integer, Ubicacion> ubicaciones;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cmbPaises.getItems().add("Pais");
		cmbDeptos.getItems().add("Depto");
		cmbMunicipios.getItems().add("Municipio");
		updateViviendas();
		try {
			updateLocations();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void cambiaVentanaPrincipal(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/source/OwnerScene.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
	}
	
	@FXML
	public void cambiaVentanaPerfil(MouseEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/source/Sidebar.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();	
	}
	
	public void search(List<Vivienda> vivs) throws SQLException, ClassNotFoundException {
		viviendas.addAll(vivs);
		updateViviendas();
	}
	
	public void updateViviendas() {
		grid.getChildren().clear();
		try{
			numPropiedades.setText(viviendas.size()+" Viviendas encontradas con el filtro especificado");
			for(int i=0;i<viviendas.size();i++){
				FXMLLoader fxmlLoader=new FXMLLoader();		
				fxmlLoader.setLocation(getClass().getResource("/source/propiedades.fxml"));
				AnchorPane anchorpane=fxmlLoader.load();		
				propiedadesController propiController = fxmlLoader.getController();
				propiController.setData(viviendas.get(i));
				grid.add(anchorpane, 1, i+1);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@FXML
	public void masHabs(MouseEvent event) {
		cantHabs++;
		habitaciones.setText(cantHabs+"");
		if(cantHabs==1) {
			menosHabitaciones.setDisable(false);
		}
	}
	
	@FXML
	public void menosHabs(MouseEvent event) {
		if(cantHabs==1) {
			cantHabs--;
			habitaciones.setText("-");
			menosHabitaciones.setDisable(true);
		}else if(cantHabs>1) {
			cantHabs--;
			habitaciones.setText(cantHabs+"");
		}
	}
	
	@FXML
    void cancelSelection(ActionEvent event) {
		cmbPaises.setValue("Pais");
		cmbDeptos.setValue("Depto");
		cmbMunicipios.setValue("Municipio");
    }
	
	@FXML
    void selectCasa(MouseEvent event) {
		tipoVivienda = "Casa";
		btnCasa.setStyle("-fx-background-color:  #85bc8c;\n" 
				+"    -fx-background-radius: 20;");
		btnApto.setStyle("-fx-background-color: grey;\n" 
				+"    -fx-background-radius: 20;");
    }
	
	@FXML
    void selectApto(MouseEvent event) {
		tipoVivienda = "Apartamento";
		btnCasa.setStyle("-fx-background-color: grey;\n" 
				+"    -fx-background-radius: 20;");
		btnApto.setStyle("-fx-background-color:  #85bc8c;\n" 
				+"    -fx-background-radius: 20;");
    }
	
	@FXML
    void clearSelection(MouseEvent event) {
		tipoVivienda = null;
		btnCasa.setStyle("-fx-background-color:  #85bc8c;\n" 
				+"    -fx-background-radius: 20;");
		btnApto.setStyle("-fx-background-color:  #85bc8c;\n" 
				+"    -fx-background-radius: 20;");
    }
	
	@FXML
	public void addLocation(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/source/AddLocationScene.fxml"));
        Parent root = loader.load();
        
        AddLocationSceneController controlador = loader.getController();
//      controlador.initAttributtes(personas);
        
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
	public void searchVivs(MouseEvent event) throws IOException, NumberFormatException, ClassNotFoundException, SQLException {
		if(!ubis.isEmpty()) {
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
			viviendas.clear();
			viviendas.addAll(Queries.buscarPropiedades(paises, deptos, municipios, tipoVivienda, cantHabs,
					arrMin, arrMax));
			updateViviendas();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar una ubicacion");
            alert.showAndWait();
		}
	}
	
	public void updateLocations() throws ClassNotFoundException, SQLException {
		this.ubicaciones = Queries.obtenerUbicacion();
	}
	
	public void setLocations(List<String> ps, List<String> ds, List<String> ms) {
		paises.addAll(ps);
		cmbPaises.getItems().addAll(ps);
		
		deptos.addAll(ds);
		cmbDeptos.getItems().addAll(ds);
		
		municipios.addAll(ms);
		cmbMunicipios.getItems().addAll(ms);
		
		for(int i=0;i<ps.size();i++) {
			String ubi = ps.get(i);
			if (!ds.get(i).equals("sin filtro")) {
				ubi += ","+ds.get(i);
				if(!ms.get(i).equals("sin filtro")) {
					ubi += ","+ms.get(i);
				}
			}
			ubis.add(ubi);
		}
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
