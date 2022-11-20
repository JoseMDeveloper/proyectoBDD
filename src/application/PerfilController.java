package application;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;
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
	@FXML
    private Button eliminar;
	String contra;
	float maximo=0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Usuario usuario;
		String Regex="^[a-zA-Z0-9]{3,25}$",RegexC="^([.\\w]{1,64}@)\\w{1,}\\.[.\\w]{1,}";
		usuario=Sesion.getUser();
		textfieldUsuario.setText(usuario.getNombredeusuario());
		
		textfieldNombre.setText(usuario.getNombre());
		if(!usuario.getNombre().equals(Regex)) {
			  Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText(null);
	            alert.setTitle("Error");
	            alert.setContentText("Nombre de usuario no valido");
	            alert.showAndWait();
		}
		textfieldApeliido.setText(usuario.getApellido());
		textfieldCorreo.setText(usuario.getCorreo());
		if(!usuario.getCorreo().equals(RegexC)) {
			  	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText(null);
	            alert.setTitle("Error");
	            alert.setContentText("Correo no valido");
	            alert.showAndWait();
		}
		textfieldContra.setText("abcdefgh");
		
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
//----------------Cambiar estado del boton ------------------------------------------	
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
//---------------------------------------------------------------------------------------------------------------------------------------//
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
	
	@FXML
	public void eliminar(MouseEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText("¿Esta suguro de eliminar su cuenta?\nEsta accion no se puede revertir");
		Optional<ButtonType> action = alert.showAndWait();
		if (action.get() == ButtonType.OK) {
			try {
				Queries.eliminarCuenta();
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setHeaderText(null);
				alert1.setTitle("Cuenta Eliminada");
				alert1.setContentText("Sea ha eliminado su cuenta\nSe le desconectará de la aplicacion");
				alert1.showAndWait();
				
				Sesion.setUser(null);
		        
		        Parent root = FXMLLoader.load(getClass().getResource("/source/LoginScene.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.centerOnScreen();
			} catch (ClassNotFoundException | SQLException e) {
				Alert alert2 = new Alert(Alert.AlertType.ERROR);
				alert2.setHeaderText(null);
				alert2.setTitle("Error");
				alert2.setContentText("La eliminacion no pudo realizarse");
				alert2.showAndWait();
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
