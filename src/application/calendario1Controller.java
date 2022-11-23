package application;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class calendario1Controller {
	@FXML
	private DatePicker fecha;
	@FXML
	private Text texto;

	

	public void funcionaaa(ActionEvent funcionaaa)
	
	{
		try
		{
		LocalDate mifecha = fecha.getValue();
		String x=mifecha.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
		texto.setText(x);
		System.out.println(mifecha.format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
		}
		catch(Exception a)
		{
			
		}
		
	}
	
}
