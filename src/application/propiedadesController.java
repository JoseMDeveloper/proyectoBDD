package application;

import java.awt.Image;
import java.io.IOException;
import java.sql.SQLException;

import connection.Queries;
import dataClass.Sesion;
import dataClass.Vivienda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class propiedadesController {
	@FXML
	private Label nombrepropi;
	@FXML
	private Label ubicacion;
	@FXML
	private Label descripcion;
	@FXML
	private Label precio;
	@FXML
	private Label habi;
	@FXML
	private Label tipo;
	@FXML
	private ImageView imagenpropi;
	@FXML
    private Button agregarVisita;
	@FXML
    private Button rentar;

	private Vivienda vivienda;
	
	public void setData(Vivienda vivienda)
	{
		this.vivienda=vivienda;
		nombrepropi.setText("ID: "+vivienda.getId()+"");
		ubicacion.setText(vivienda.getPais());
		descripcion.setText("Descripcion: "+vivienda.getDescripcion());
		precio.setText("Precio: $"+vivienda.getPrecioRentaMensual());
		habi.setText("Cantidad habitaciones: "+vivienda.getCantHabitaciones()+"");
		tipo.setText("Tipo: "+vivienda.getTipo());
	}
	
	@FXML
	void agregarVisita(MouseEvent event) {
		try {
			if(Queries.visitaUsuarioVivienda(Sesion.getUser().getId(), vivienda.getId())){
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText(null);
	            alert.setTitle("Error");
	            alert.setContentText("La vivienda ya se encuentra en su lista de visitas");
	            alert.showAndWait();
			}else if(Sesion.getUser().getMaximo()<vivienda.getPrecioRentaMensual()) {					
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("El precio de la vivienda es demaciado elevado\n"
						+ "Puede actualizar su perfil en MI PERFIL para realizar esta accion");
				alert.showAndWait();
			}else {
				Queries.CrearVisita(Sesion.getUser().getId(), vivienda.getId());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Agregar a la lista de visitas");
				alert.setContentText("Se agrego correctamente");
				alert.showAndWait();
			}
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se pudo agregar la vivienda a la lista de visitas");
            alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	@FXML
	void rentar(MouseEvent event) throws IOException {
//		try {
//			if(Queries.visitaUsuarioVivienda(Sesion.getUser().getId(), vivienda.getId())){
//				Alert alert = new Alert(Alert.AlertType.ERROR);
//	            alert.setHeaderText(null);
//	            alert.setTitle("Error");
//	            alert.setContentText("La vivienda ya se encuentra en su lista de visitas");
//	            alert.showAndWait();
//			}else
			if(Sesion.getUser().getMaximo()<vivienda.getPrecioRentaMensual()) {					
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("El precio de la vivienda es demaciado elevado\n"
						+ "Puede actualizar su perfil en MI PERFIL para realizar esta accion");
				alert.showAndWait();
			}else {
//				Queries.CrearVisita(Sesion.getUser().getId(), vivienda.getId());
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/source/GenerarFactura.fxml"));
		        Parent root = loader.load();
		        
		        GenerarFacturaController controlador = loader.getController();
//		        controlador.initAttributtes(personas);
		        
		        Scene scene = new Scene(root);
		        Stage stage = new Stage();
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.setScene(scene);
		        stage.showAndWait();
			}
//		} catch (ClassNotFoundException | SQLException e) {
//			Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setTitle("Error");
//            alert.setContentText("No se pudo agregar la vivienda a la lista de visitas");
//            alert.showAndWait();
//			e.printStackTrace();
//		}
	}
}
