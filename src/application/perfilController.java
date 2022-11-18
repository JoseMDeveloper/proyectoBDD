package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class perfilController {

	@FXML
    private Button guardar;
	@FXML
    private TextField textfieldUsuario;
	@FXML
    private TextField textfieldNombre;
	@FXML
    private TextField textfieldApeliido;
	@FXML
    private TextField textfieldCorreo;
	@FXML
    private TextField textfieldTipoCuenta;
	
	public void Guardar(MouseEvent event)
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Seguro que desea guardar los cambios?");
        alert.showAndWait();
	}
}
