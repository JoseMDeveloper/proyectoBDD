package application;

import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import dataClass.tipoPago;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class MetodoPagoController implements Initializable{

	private List <String> tipospago=new ArrayList<>();
	private Integer[] meses= {1,2,3,4,5,6,7,8,9,10,11,12};
    @FXML
    private Button agregarButton;

    @FXML
    private TextField anoField;

    @FXML
    private ChoiceBox<Integer> mesField;

    @FXML
    private TextField montoField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField numeroField;

    @FXML
    private Text numeroText;
    @FXML
    private Text venciText;
    @FXML
    private Text nombreText;
    
    @FXML
    private Label tipPagoText;

    @FXML
    private ComboBox<String> tipoPagoField;
    String tipago;
    private List<tipoPago> paguitos=new ArrayList<>();
    tipoPago tempo;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		anoField.setVisible(false);
		numeroText.setVisible(false);
		nombreField.setVisible(false);
		mesField.setVisible(false);
		venciText.setVisible(false);
		nombreText.setVisible(false);
		numeroField.setVisible(false);	
		mesField.getItems().addAll(meses);
		tipospago.add("Efectivo");
		tipospago.add("Bono");
		tipospago.add("Tarjeta Credito");
		tipoPagoField.getItems().addAll(tipospago);
		tipoPagoField.setOnAction(this::tipoPago);
	}
	public void tipoPago(ActionEvent event)
	{
		tipago=tipoPagoField.getValue();
		if(tipago.equals("Efectivo"))
		{
			numeroText.setVisible(false);
			numeroField.setVisible(false);	
			nombreText.setVisible(false);
			nombreField.setVisible(false);
			venciText.setVisible(false);
			anoField.setVisible(false);
			mesField.setVisible(false);
			
		}
		else if(tipago.equals("Bono"))
		{
			numeroText.setVisible(true);
			numeroField.setVisible(true);	
			nombreText.setVisible(false);
			nombreField.setVisible(false);
			venciText.setVisible(false);
			anoField.setVisible(false);
			mesField.setVisible(false);
			
		}
		else if(tipago.equals("Tarjeta Credito"))
		{
			numeroText.setVisible(true);
			numeroField.setVisible(true);	
			nombreText.setVisible(true);
			nombreField.setVisible(true);
			venciText.setVisible(true);
			anoField.setVisible(true);
			mesField.setVisible(true);
		}	
	}
	public void crear(String tipo) 
	{
		try
		{
			if(tipo.equals("Efectivo"))
			{
				tempo=new tipoPago(Float.parseFloat(montoField.getText()));
				paguitos.add(tempo);
			}
			else if(tipo.equals("Bono"))
			{
				tempo=new tipoPago(Float.parseFloat(montoField.getText()),Long.parseLong(numeroField.getText()));	
				paguitos.add(tempo);
			}
			else if(tipo.equals("Tarjeta Credito"))
			{
				tempo=new tipoPago(Float.parseFloat(montoField.getText()),Long.parseLong(numeroField.getText()),nombreField.getText(),Integer.parseInt(anoField.getText()),mesField.getValue());
				paguitos.add(tempo);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		};
	}
	public void agregar(MouseEvent event)
	{
		crear(tipoPagoField.getValue());
		Stage stage = (Stage) this.agregarButton.getScene().getWindow();
		stage.close();
	}
	public List<tipoPago> getpaguitos()
	{
		return paguitos;
		
	}
}
