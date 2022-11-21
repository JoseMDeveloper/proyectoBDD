package application;

import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dataClass.Vivienda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class SearchScene2Controller extends PrincipalAbstractController implements Initializable{
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
    private Label cantHabitaciones;
	
	@FXML
	private Label cuenta;

	private List<Vivienda> viviendas=new ArrayList<>();
	// Santi aca debes conectar el buscador, y ponerle a vivi los datos de cada vivienda que encontro, el resto se hace solo

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateViviendas();
	}
	
	@FXML
	public void cambiaVentanaPrincipal(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/source/ClientScene.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
	}
	
	@FXML
	public void cambiaVentanaPerfil(MouseEvent event)throws IOException
	{
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
		try{
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
}
