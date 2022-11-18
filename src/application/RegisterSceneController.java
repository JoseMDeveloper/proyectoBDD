package application;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javafx.util.Duration;
import java.util.ResourceBundle;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import connection.Queries;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisterSceneController implements Initializable{
	
	private boolean iniciandoSesion = false;//Iniciar sesion = false, Registrar = true
	
	@FXML private Pane selectTipo;
	@FXML private ToggleButton botonCliente;
	@FXML private ToggleButton botonDueno;
	@FXML private Label error;
	@FXML private Label slogan;
	@FXML private Label texto;
	@FXML private Label profe;
	@FXML private Label recibir;
	@FXML private BorderPane pantalla1;
	@FXML private AnchorPane ancla2;
	@FXML private TextField enterMail;
	@FXML private TextField enterName;
	@FXML private PasswordField enterPassword;
	@FXML private Button boton3;
	@FXML private Button boton2;
	@FXML private AnchorPane ancla1;
	@FXML private Button boton1;
	@FXML private Label seTeOlvido;
	
	@Override
 	public void initialize(URL arg0, ResourceBundle arg1) {
		enterName.setText("Admin");
		enterPassword.setText("teamo");
		
		setPerspectiveLogIn();
	}
	
	//Cambia la posicion y hace la traslacion cuando se le da al boton registrar 
	@FXML
	public void registrar(MouseEvent event) {
		if(iniciandoSesion==false) {
			if(enterName.getTranslateY()<=35) {
				enterName.setTranslateY(5);
				enterPassword.setTranslateY(10);
			}
				TranslateTransition slide = new TranslateTransition();
				slide.setDuration (Duration. seconds(0.7));
				slide.setNode(ancla1);
				slide.setByX(325);
				slide.play();
				
				ancla2.setTranslateX(-415);
				slide.setOnFinished((e->{}));
				setPerspectiveSignIn();
		}
	}
	
	//Cambia la posicion y hace la traslacion cuando se le da al boton iniciar sesion 
	public void iniciarSesion(MouseEvent event) {
		if(iniciandoSesion==true){	
			seTeOlvido.setVisible(true);
			if(enterName.getLayoutX()>=52.8){
				enterName.setTranslateY(-30);
				enterPassword.setTranslateY(-20);
				seTeOlvido.setTranslateY(-20);
			}
			TranslateTransition slide = new TranslateTransition();
			slide.setDuration (Duration.seconds(0.7));
			slide.setNode(ancla1);
			slide.setByX(-325);
			slide.play();
			
			ancla2.setTranslateX(0);
			slide.setOnFinished((e->{}));
			
			setPerspectiveLogIn();
		}
	}
	
	//Interaccion con el boton siguiente para crear o ingresar un usuario
	public void siguiente(MouseEvent event) throws NoSuchAlgorithmException, IOException, InvalidKeyException, 

			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, ClassNotFoundException, SQLException {
		//obtener texto de los fields
		error.setAlignment(Pos.CENTER);
		String nombre = enterName.getText().strip();
		String mail = enterMail.getText().strip();
		String contra = enterPassword.getText();
		int tipoUsuario = 1;
		String nombreRegex="^[a-z0-9]{3,25}$";
		String mailRegex="^([.\\w]{1,64}@)\\w{1,}\\.[.\\w]{1,}";
		//No acepta campos vacios
		
		if(!(nombre.isEmpty() || contra.isBlank())) {
			if(!nombre.matches(nombreRegex)){
				showEventMessage("Nombre invalido", "#fcc0bf", "#b12727");
			}
			if(!iniciandoSesion) {//Iniciar sesion
				if(Queries.validSesion(nombre, contra)) {
					showEventMessage("!Ingreso Correctamente!", "#91e291", "#578857");
					cambiaVentanaPrincipal(event);
				} else {
					error.setVisible(true);

				}
			} 	
			else if(!mail.isEmpty()) {
				if(!mail.matches(mailRegex)) {

					showEventMessage("Nombre de usuario invalido", "#fcc0bf", "#b12727");

				} else {
					Queries.createUser(nombre, mail, contra, tipoUsuario);
					showEventMessage("!Usuario creado correctamente!", "#91e291", "#578857");
				}
			}
			else{
				showEventMessage("Debe llenar todos los campos", "#fcc0bf", "#b12727");
			}
		}
		else{
			showEventMessage("Debe llenar todos los campos", "#fcc0bf", "#b12727");
		}
	}
	public void Dueno(){
		
		if(botonDueno.isSelected() && botonCliente.isSelected()){
			botonCliente.setSelected(!botonCliente.isSelected());;
		}
		
		
	}
	public void Cliente(){
		if(botonCliente.isSelected() && botonDueno.isSelected()){
			
			botonDueno.setSelected(!botonDueno.isSelected());;	
		}
	}
	
	public void setPerspectiveLogIn() {
		boton1.setVisible(true);
		boton2.setVisible(false);
		enterMail.setVisible(false);
		enterName.setTranslateY(-35);
		enterPassword.setTranslateY(-20);
		seTeOlvido.setTranslateY(-20);
		boton2.setVisible(false);
		profe.setVisible(false);
		error.setVisible(false);
		selectTipo.setVisible(false);
		enterMail.setVisible(false);
		iniciandoSesion = false;
		
		boton3.setText("Iniciar sesion");
		recibir.setText("Hola de Nuevo!");
		texto.setText("Para iniciar sesion en tu cuenta, ingrese su direccion de correo electronico y su contrase�a.");
		slogan.setText("No buscamos tu piso, encontramos tu hogar, y si quieres hacer parte de esta familia dale registrar");
	}
	
	public void setPerspectiveSignIn() {
		boton1.setVisible(false);
		boton2.setVisible(true);
		profe.setVisible(true);
		error.setVisible(false);
		selectTipo.setVisible(true);
		enterMail.setVisible(true);
		seTeOlvido.setVisible(false);
		iniciandoSesion=true;
		
		boton3.setText("Registrar");
		recibir.setText("Bienvenido!");
		texto.setText("Para crear una nueva cuenta, ingresa tu nombre de usuario, correo electronico y establece una contrase�a.");
		slogan.setText("Los mejores profesionales a tu servicio, Tu casa en buenas manos");
	}
	
	public void showEventMessage(String message, String color, String borderColor) {
		error.setStyle("-fx-background-color: "+color+";-fx-border-color: "+borderColor+";-fx-background-radius: 9;-fx-border-radius: 9;");
		error.setText(message);
		error.setVisible(true);
	}
	
	public void cambiaVentanaPrincipal(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/source/PrincipalScene.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
//		ventanaPrinci.setX(-10);
//		ventanaPrinci.setY(0);
//		stage.setMaximized(true);
//		stage.setResizable(false);
//		ventanaPrinci.showAndWait();
	}
}