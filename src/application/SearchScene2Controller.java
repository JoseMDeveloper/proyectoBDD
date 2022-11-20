package application;

import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dataClass.Vivienda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;

import javafx.scene.image.ImageView;

import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class SearchScene2Controller implements Initializable{
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

	private List<Vivienda> vivienda=new ArrayList<>();
	// Santi aca debes conectar el buscador, y ponerle a vivi los datos de cada vivienda que encontro, el resto se hace solo
	private List<Vivienda> obtenerInfo()
	{
		 List<Vivienda> vivienda=new ArrayList<>();
		 Vivienda vivi;
		 for(int i=0;i<5;i++)
		 {
			 vivi=new Vivienda(1,"casa","calle3",3,4123124F,"15 abril","activo","la casa es super bonita"
					 ,"Colombia"); 
			 vivienda.add(vivi);
		 }
		 return vivienda;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vivienda.addAll(obtenerInfo());
		int column=0;
		int row=0;
		try
		{
			for(int i=0;i<vivienda.size();i++)
			{
				FXMLLoader fxmlLoader=new FXMLLoader();		
				fxmlLoader.setLocation(getClass().getResource("/source/propiedades.fxml"));
					AnchorPane anchorpane=fxmlLoader.load();		
				propiedadesController propiController= fxmlLoader.getController();
				propiController.setData(vivienda.get(i));
				row++;
				grid.add(anchorpane, 1, row);
				
			}
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
