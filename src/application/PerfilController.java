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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import application.EditarContraController;

public class PerfilController implements Initializable{
	@FXML
    private Button guardar;
	@FXML
    private Button a;
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
	@FXML
    private TextField textfieldContra;
	@FXML
    private Button editarNombre;
	@FXML
    private Button editarApe;
	@FXML
    private Button editarCorreo;
	@FXML
    private Button editarContra;
	@FXML
    private Button editarRenta;
	@FXML
    private ImageView cerrar;
	String contra;
	float maximo=0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Usuario usuario;
		usuario=Sesion.getUser();
		textfieldUsuario.setText(usuario.getNombredeusuario());
		textfieldNombre.setText(usuario.getNombre());
		textfieldApeliido.setText(usuario.getApellido());
		textfieldCorreo.setText(usuario.getCorreo());
		textfieldContra.setText(usuario.getContrasena());
		
		if(usuario.getIDtipousuario()==1){
			textfieldTipoCuenta.setText("Arrendatario");	
		}else if(usuario.getIDtipousuario()==2){
			textfieldTipoCuenta.setText("Propietario");	
		}else if(usuario.getIDtipousuario()==3){
			textfieldTipoCuenta.setText("Administrador");	
		}
		textfieldrenta.setText(maximo+"");

	}
	
	
	public void Guardar(MouseEvent event) throws NumberFormatException, ClassNotFoundException, NoSuchAlgorithmException, SQLException, IOException{
//		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/source/editarContra.fxml"));
//		editarContraController controlador= fxmlLoader.getController();
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Seguro que desea guardar los cambios?");
        alert.showAndWait().ifPresent(response -> 
        {
            if (response == ButtonType.OK) {
            	try {
					Queries.updateUser(textfieldUsuario.getText(),textfieldCorreo.getText(),contra,textfieldNombre.getText(),
							textfieldApeliido.getText(),Float.parseFloat(textfieldrenta.getText()));
				} catch (NumberFormatException | ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        
        });
	}
	
	public void editarNombre(MouseEvent event)
	{
		if(editarNombre.getText().equals("Editar")){
			textfieldNombre.setEditable(true);
			editarNombre.setText("Guardar");
		}
		else if(editarNombre.getText().equals("Guardar")){
			textfieldNombre.setEditable(false);
			editarNombre.setText("Editar");
		}
	}
	
	public void editarApe(MouseEvent event){
		if(editarApe.getText().equals("Editar")){
			textfieldApeliido.setEditable(true);
			editarApe.setText("Guardar");
		}
		else if(editarApe.getText().equals("Guardar")){
			textfieldApeliido.setEditable(false);
			editarApe.setText("Editar");
		}
	}
	
	public void editarCorreo(MouseEvent event){
		if(editarCorreo.getText().equals("Editar")){
			textfieldCorreo.setEditable(true);
			editarCorreo.setText("Guardar");
		}
		else if(editarCorreo.getText().equals("Guardar")){
			textfieldCorreo.setEditable(false);
			editarCorreo.setText("Editar");
		}
	}
	
	public void editarRenta(MouseEvent event){
		if(editarRenta.getText().equals("Editar")){
			textfieldrenta.setEditable(true);
			editarRenta.setText("Guardar");
		}
		else if(editarRenta.getText().equals("Guardar")){
			textfieldrenta.setEditable(false);
			editarRenta.setText("Editar");
		}
	}
	
	public void editarContra(MouseEvent event) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, SQLException{
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/source/editarContra.fxml"));
		Parent root1=(Parent)fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.centerOnScreen();
		stage.showAndWait();
		EditarContraController controlador1= fxmlLoader.getController();
		contra=controlador1.getResultado();
	}
	
	public void mostrar(MouseEvent event){

	}
}
