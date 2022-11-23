package application;




import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.Queries;
import dataClass.Sesion;
import dataClass.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditarContraController implements Initializable{
	@FXML
	private PasswordField viejaContra;
	@FXML
	private PasswordField nuevaContra;
	@FXML
	private Button actualizar;
	@FXML
	private Label error;
	
	String nuevaContra1=null;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		error.setVisible(false);
		
	}
	@FXML
	public void guardarContra(MouseEvent event) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException, IOException
	{
		nuevaContra1=null;
		Usuario usuario;
		usuario=Sesion.getUser();
		if(Queries.validSesion(usuario.getNombredeusuario(), viejaContra.getText()))
		{
			nuevaContra1=nuevaContra.getText();
			if(viejaContra.getText()==null || nuevaContra==null ) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText(null);
	            alert.setTitle("Error");
	            alert.setContentText("No puedes dejar ninguno de los 2 campos vacios");
	            alert.showAndWait();
			}
			error.setVisible(false);
			Stage stage = (Stage) this.actualizar.getScene().getWindow();
    		stage.close();
			}
		
		else
		{
			error.setVisible(true);
		}
		
	}
	public String getResultado()
	{
		return nuevaContra1;
	}
}
