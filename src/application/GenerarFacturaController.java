package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;

public class GenerarFacturaController implements Initializable{
	@FXML
	private Button agregarPago;
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
	
	private Integer cantMeses=1;
	private Float totalPagar;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	public void menosMeses(MouseEvent event) {
		if(cantMeses==2) {
			cantMeses--;
			meses.setText(cantMeses+"");
			menosMeses.setDisable(true);
		}else if(cantMeses>1) {
			cantMeses--;
			meses.setText(cantMeses+"");
		}
	}
	
	@FXML
	public void masMeses(MouseEvent event) {
		cantMeses++;
		meses.setText(cantMeses+"");
		if(cantMeses==2) {
			menosMeses.setDisable(false);
		}
	}
}
