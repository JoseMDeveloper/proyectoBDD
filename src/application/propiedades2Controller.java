package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import connection.Queries;
import dataClass.Sesion;
import dataClass.Vivienda;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class propiedades2Controller {
	@FXML
	private ImageView imagenpropi;
	@FXML
	private Label ubicacion;
	@FXML
	private Label nombrepropi;
	@FXML
	private Label descripcion;
	@FXML
	private Label precio;
	@FXML
	private Label tipo;
	@FXML
	private Label habi;
	@FXML
	private Button verVisitas;
	@FXML
	private Button borrar;
	@FXML
	private Button actualizar;
	@FXML
	private Label fecha;

	private Vivienda vivienda;
	private static Integer id;
	private static Float preci;
	
	public void setData(Vivienda vivienda)
	{
		this.vivienda=vivienda;
		id=vivienda.getId();
		nombrepropi.setText("ID: "+id+"");
		ubicacion.setText(vivienda.getPais());
		descripcion.setText("Descripcion: "+vivienda.getDescripcion());
		preci=vivienda.getPrecioRentaMensual();
		precio.setText("Precio: $"+preci);
		habi.setText("Cantidad habitaciones: "+vivienda.getCantHabitaciones()+"");
		tipo.setText("Tipo: "+vivienda.getTipo());
		fecha.setText(vivienda.getFecha());
	}
	public void borraPropi(MouseEvent event)
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmaci�n");
		alert.setContentText("�Esta suguro de eliminar su cuenta?\nEsta accion no se puede revertir");
		Optional<ButtonType> action = alert.showAndWait();
		if (action.get() == ButtonType.OK) 
		{
			try {
				Queries.eliminarpropi(vivienda.getId());
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setHeaderText(null);
				alert1.setTitle("Propiedad Eliminada");
				alert1.setContentText("Sea ha eliminado la propiedad\nSe le desconectar� de la aplicacion");
				alert1.showAndWait();
				
				
				}
			catch (ClassNotFoundException | SQLException e) 
				{
					Alert alert2 = new Alert(Alert.AlertType.ERROR);
					alert2.setHeaderText(null);
					alert2.setTitle("Error");
					alert2.setContentText("La eliminacion no pudo realizarse");
					alert2.showAndWait();
					e.printStackTrace();
				} 
			catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
	}
	
	
	
	public Integer getnombre()
	{
		return id;
		
	}
	public Float getprecio()
	{
		return preci;
	}
}
