package application;

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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class perfilController implements Initializable{

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
	@FXML
    private TextField textfieldrenta;
	float maximo=0;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Usuario usuario;
		usuario=Sesion.getUser();
		String Regex="^[a-zA-Z0-9]{3,25}$";
		textfieldUsuario.setText(usuario.getNombredeusuario());
		if (!usuario.getNombredeusuario().equals(Regex)) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Nombre de usuario no valido");
            alert.showAndWait();
		}
		textfieldNombre.setText(usuario.getNombre());
		textfieldApeliido.setText(usuario.getApellido());
		textfieldCorreo.setText(usuario.getCorreo());
		if(usuario.getIDtipousuario()==1)
		{
			textfieldTipoCuenta.setText("Arrendatario");	
		}
		textfieldrenta.setText(maximo+"");

	}
	public void Guardar(MouseEvent event) throws NumberFormatException, ClassNotFoundException, NoSuchAlgorithmException, SQLException
	{
		Queries.updateUser(textfieldUsuario.getText(),textfieldCorreo.getText(),null,textfieldNombre.getText(),
				textfieldApeliido.getText(),Float.parseFloat(textfieldrenta.getText()));
//		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setHeaderText(null);
//        alert.setTitle("Error");
//        alert.setContentText("Seguro que desea guardar los cambios?");
//        alert.showAndWait();
	}
	
	
}
