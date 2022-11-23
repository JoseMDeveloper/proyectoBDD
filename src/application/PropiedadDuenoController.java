package application;

import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import connection.DBConnection;
import connection.Queries;
import dataClass.Factura;
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
    private TextField arriendo;
    @FXML
    private Button btnArrendar;
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
    
    private String ubi;
	private List<Vivienda> viviendas = new ArrayList<>();
	private Map<Integer, Ubicacion> ubicaciones;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cmbPaises.getItems().add("Pais");
		cmbDeptos.getItems().add("Depto");
		cmbMunicipios.getItems().add("Municipio");
//		viviendas.addAll();
		try {
			misViviendas();
			updateLocations();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		updateViviendas();
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
	
	public void updateViviendas() {
		grid.getChildren().clear();
		try{
			numPropiedades.setText("Usted tiene "+viviendas.size()+" Viviendas");
			for(int i=0;i<viviendas.size();i++){
				FXMLLoader fxmlLoader=new FXMLLoader();		
				fxmlLoader.setLocation(getClass().getResource("/source/propiedades2.fxml"));
				AnchorPane anchorpane=fxmlLoader.load();		
				propiedades2Controller propiController = fxmlLoader.getController();
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/source/SetLocationScene.fxml"));
        Parent root = loader.load();
        
        SetLocationSceneController controlador = loader.getController();
//      controlador.initAttributtes(personas);
        
        controlador.updateLocations(ubicaciones);
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        clearUbicaciones();
        ubi=controlador.getResult();
	}
	
	public void updateLocations() {
		try {
			this.ubicaciones = Queries.obtenerUbicacion();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setLocations(String p, String d, String m) {
		paises.add(p);
		cmbPaises.getItems().add(p);
		
		deptos.add(d);
		cmbDeptos.getItems().add(d);
		
		municipios.add(m);
		cmbMunicipios.getItems().add(m);
	}
	
	public void clearUbicaciones() {
		ubi=null;
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
	
	@FXML
	public void arrendar(MouseEvent event) throws IOException {
		if (ubi==null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar una ubicacion");
            alert.showAndWait();
		}else {
			try {
				int idTipoViv=0;
				if (tipoVivienda.equals("Apartamento")) {
					idTipoViv = 1;
				}else {
					idTipoViv = 2;
				}
				int idUbi=0;
				String[] splitUbi = ubi.split(",");
				for(Integer i : ubicaciones.keySet()){
					Ubicacion u = ubicaciones.get(i);
					if(u.getPais().equals(splitUbi[0]) && u.getDepartamento().equals(splitUbi[1]) && u.getMunicipio().equals(splitUbi[2])) {
						idUbi=i;
					}
				}
				int idviv=Queries.createpropi("d", cantHabs, Float.parseFloat(arriendo.getText()), null, idUbi, idTipoViv);
				Queries.insertTransaccionPago(Sesion.getUser().getId(), idviv,
						new Factura(null, Sesion.getUser().getCorreo(), 1F, null, null, null, null, null), new ArrayList<>());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			misViviendas();
			updateViviendas();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void misViviendas() throws ClassNotFoundException, SQLException {
		viviendas = Queries.viviendasPropietario(Sesion.getUser().getId());
	}
}
