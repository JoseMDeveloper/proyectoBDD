package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;

import javafx.scene.control.DatePicker;

import javafx.scene.input.MouseEvent;


public class calendarioController {
	@FXML
	private DatePicker fecha;
	

	public void fechaa(MouseEvent event)
	
	{
		LocalDate mifecha = fecha.getValue();
		mifecha.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
		System.out.println(mifecha.format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
		
	}
}
