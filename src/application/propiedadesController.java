package application;

import java.awt.Image;

import dataClass.Vivienda;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

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

	private Vivienda vivienda;
	public void setData(Vivienda vivienda)
	{
		this.vivienda=vivienda;
		nombrepropi.setText("ID: "+vivienda.getId()+"");
		ubicacion.setText(vivienda.getPais());
		descripcion.setText("Descripcion: "+vivienda.getDescripcion());
		precio.setText("Precio: "+vivienda.getPrecioRentaMensual()+"$");
		habi.setText("Cantidad habitaciones: "+vivienda.getCantHabitaciones()+"");
		tipo.setText("Tipo: "+vivienda.getTipo());
		
		
	}
}
