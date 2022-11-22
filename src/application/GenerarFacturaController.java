package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dataClass.tipoPago;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GenerarFacturaController implements Initializable{
	@FXML
	private Button agregarPago;
	@FXML
	private ListView<tipoPago> listView;
	@FXML
	private Label valorPagar;
	@FXML
	private Button menosMeses;
	@FXML
	private Button masMeses;
	@FXML
	private Button meses;
	@FXML
	private Button pagar;
	@FXML
	private Text cantMeses;
	
	private Integer cantMese=1;
	private Float totalPagar;
	private List<tipoPago> paguitos=new ArrayList<>();
	private ObservableList<tipoPago> tablita;
	Integer casa;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			casa=obtenerCasa();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@FXML
	public void menosMeses(MouseEvent event) {
		if(cantMese==2) {
			cantMese--;
			cantMeses.setText(cantMese+"");
			menosMeses.setDisable(true);
		}else if(cantMese>1) {
			cantMese--;
			cantMeses.setText(cantMese+"");
		}
	}
	
	@FXML
	public void masMeses(MouseEvent event) {
		cantMese++;
		cantMeses.setText(cantMese+"");
		if(cantMese==2) {
			menosMeses.setDisable(false);
		}
	}
	@FXML
	public void agregar(MouseEvent event) throws IOException 
	{
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/source/MetodoPago.fxml"));
		Parent root1=(Parent)fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.centerOnScreen();
		stage.showAndWait();
		MetodoPagoController controlador1= fxmlLoader.getController();
		paguitos.addAll(controlador1.getpaguitos());
		tablita=FXCollections.observableArrayList(paguitos);
		listView.setItems(tablita);
		
		
	}
	@FXML
	public void pago(MouseEvent event)
	{
		
	}
	public Integer obtenerCasa() throws IOException
	{
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/source/propiedades.fxml"));
		Parent root1=(Parent)fxmlLoader.load();
		propiedadesController controlador1= fxmlLoader.getController();
		return controlador1.getnombre();
	}
}
