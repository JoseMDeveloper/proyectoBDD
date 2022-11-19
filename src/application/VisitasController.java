package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class VisitasController implements Initializable{
	@FXML
	private Button Agendadas;
	@FXML
	private Button Historial;
	@FXML
	private BorderPane bp;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cargarPagina("Agendadas");

	}
	
	private void cargarPagina(String string)
	{
		Parent root =null;
		try
		{
			root=FXMLLoader.load(getClass().getResource("/source/"+string+".fxml"));
			
		}
		catch(IOException ex)
		{
			
		}
		bp.setCenter(root);
	}
	@FXML
	public void cargarHistorial(MouseEvent event) {
		cargarPagina("historial");
	}
	@FXML
	public void cargarAgendadas(MouseEvent event) {
		cargarPagina("Agendadas");
	}
}
