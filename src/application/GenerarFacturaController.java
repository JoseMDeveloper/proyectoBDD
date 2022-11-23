package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import connection.Queries;
import dataClass.Factura;
import dataClass.Sesion;
import dataClass.tipoPago;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GenerarFacturaController implements Initializable{
	@FXML
	private Button agregarPago;
	@FXML
	private TextField correo;
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
	@FXML
	private Text pagado;
	@FXML
	private CheckBox seguro;
	@FXML
	private CheckBox psicina;
	
	@FXML
	private Text faltante;
	
	@FXML
	private Text total;
	
	private Integer cantMese=1;
	private Float totalPagar;
	private List<tipoPago> paguitos=new ArrayList<>();
	private ObservableList<tipoPago> tablita;
	Integer casa;
	Float precio=0F;
	String RegexC="^([.\\w]{1,64}@)\\w{1,}\\.[.\\w]{1,}";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			casa=obtenerCasa();
			precio=obtenerpreci();
			totalPagar=precio+impuestos(precio);
			total.setText(totalPagar+"");
			pagado.setText(pagado()+"");
			faltante.setText(totalPagar+"");
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
		actualizar();
	}
	@FXML
	public void pago(MouseEvent event){
		Factura factura =new Factura(null, correo.getText(), precio, null, null, Sesion.getUser().getId(), casa, null);
		try {
			if(correo.getText()==null) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText(null);
	            alert.setTitle("Error");
	            alert.setContentText("El campo de correo no puede quedar vacio");
	            alert.showAndWait();
			}
			else if(!correo.getText().equals(RegexC)) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText(null);
	            alert.setTitle("Error");
	            alert.setContentText("No es un correo valido");
	            alert.showAndWait();
			}
			else if (totalPagar==Float.parseFloat(pagado.getText())) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText(null);
	            alert.setTitle("Error");
	            alert.setContentText("Monto Invalido");
	            alert.showAndWait();
			}
			else {
				Queries.insertTransaccionPago(Sesion.getUser().getId(), casa, factura, paguitos);				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Integer obtenerCasa() throws IOException
	{
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/source/propiedades.fxml"));
		Parent root1=(Parent)fxmlLoader.load();
		propiedadesController controlador1= fxmlLoader.getController();
		return controlador1.getnombre();
	}
	public Float obtenerpreci() throws IOException
	{
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/source/propiedades.fxml"));
		Parent root1=(Parent)fxmlLoader.load();
		propiedadesController controlador1= fxmlLoader.getController();
		return controlador1.getprecio();
	}
	public Float impuestos(Float precio)
	{
		Float IVA=0F,ICA=0F,Rete=0F;
		Float total=0F;
		IVA=(float) (precio*0.16);
		ICA=(float) (precio*0.06);
		Rete=(float)(precio*0.07);
		return total=IVA+ICA+Rete;
	}
	
	public Float pagado()
	{
		Float total=0F;
		for(int i=0;i<paguitos.size();i++)
		{
			total+=paguitos.get(i).getMonto();
		}
		return total;
	}
	
	public void segu(MouseEvent event)
	{
		boolean selecionado;
		selecionado=seguro.isSelected();
		if(selecionado)
		{
			totalPagar+=200000F;
			total.setText(totalPagar+"");
			actualizar();
		}
		else
		{
			totalPagar-=200000F;
			total.setText(totalPagar+"");
			actualizar();
		}
	}
	public void pis(MouseEvent event)
	{
		boolean selecionado;
		selecionado=psicina.isSelected();
		if(selecionado)
		{
			totalPagar+=100000F;
			total.setText(totalPagar+"");
			actualizar();
		}
		else
		{
			totalPagar-=100000F;
			total.setText(totalPagar+"");
			actualizar();
		}
	}
	public void actualizar()
	{
		pagado.setText(pagado()+"");
		faltante.setText(totalPagar-pagado()+"");
		
	}
}
