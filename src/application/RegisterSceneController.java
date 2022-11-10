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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RegisterSceneController implements Initializable{
	private boolean perspective = false;//Iniciar sesion = false, Registrar = true
	 
	@FXML
	private Label error;
	@FXML
	private Label slogan;
	@FXML
	private Label texto;
	@FXML
	private Label profe;
	@FXML 
	private Label recibir;
	@FXML
	private BorderPane pantalla1;
	@FXML
	private AnchorPane ancla2;
	@FXML
	private TextField enterName;
	@FXML
	private TextField enterEmail;
	@FXML
	private PasswordField enterPassword;
	@FXML
	private Button boton3;
	@FXML
	private Button boton2;
	@FXML
	private AnchorPane ancla1;
	@FXML
	private Button boton1;
	@FXML
	private Label seTeOlvido;
	//Variables para abrir la ventana principal
	private Stage ventanaPrinci;
	private Scene EscenarioPrinci;
	private Parent rootPrinci;
	
	@Override
 	public void initialize(URL arg0, ResourceBundle arg1) {
		enterName.setVisible(false);
		enterEmail.setTranslateY(-35);
		enterPassword.setTranslateY(-20);
		seTeOlvido.setTranslateY(-20);
		boton2.setVisible(false);
		profe.setVisible(false);
		error.setVisible(false);
	}
	
	//Cambia la posicion y hace la traslacion cuando se le da al boton registrar 
	@FXML
	public void registrar(MouseEvent event) {
		if(perspective==false) {	
			enterName.setVisible(true);
			seTeOlvido.setVisible(false);
			if(enterEmail.getTranslateY()<=35) {
				enterEmail.setTranslateY(10);
				enterPassword.setTranslateY(20);
			}
				TranslateTransition slide = new TranslateTransition();
				slide.setDuration (Duration. seconds(0.7));
				slide.setNode(ancla1);
				slide.setByX(325);
				slide.play();
				
				ancla2.setTranslateX(-415);
				slide.setOnFinished((e->{}));
				perspective=true;
			
				boton1.setVisible(false);
				boton2.setVisible(true);
				boton3.setText("Registrar");
				recibir.setText("!Bienvenido!");
				profe.setVisible(true);
				texto.setText("Para crear una nueva cuenta, ingresa tu nombre de usuario, correo electronico y establece una contraseña.");
				slogan.setText("Los mejores profesionales a tu servicio, Tu casa en buenas manos");
				error.setVisible(false);
		}
	}
	
	//Cambia la posicion y hace la traslacion cuando se le da al boton iniciar sesion 
	public void iniciarSesion(MouseEvent event) {
		if(perspective==true){	
			seTeOlvido.setVisible(true);
			if(enterEmail.getLayoutX()>=52.8){
				enterEmail.setTranslateY(-30);
				enterPassword.setTranslateY(-20);
				seTeOlvido.setTranslateY(-20);
			}
			enterName.setVisible(false);
			TranslateTransition slide = new TranslateTransition();
			slide.setDuration (Duration.seconds(0.7));
			slide.setNode(ancla1);
			slide.setByX(-325);
			slide.play();
			
			ancla2.setTranslateX(0);
			slide.setOnFinished((e->{}));
			perspective=false;
			
			boton1.setVisible(true);
			boton2.setVisible(false);
			boton3.setText("Iniciar sesion");
			recibir.setText("¡Hola de Nuevo!");
			profe.setVisible(false);
			texto.setText("Para iniciar sesion en tu cuenta, ingrese su direccion de correo electronico y su contraseña.");
			slogan.setText("No buscamos tu piso, encontramos tu hogar, y si quieres hacer parte de esta familia dale registrar");
			error.setVisible(false);
		}
	}
	
	//Interaccion con el boton siguiente para crear o ingresar un usuario
	public void siguiente(MouseEvent event) throws NoSuchAlgorithmException, IOException, InvalidKeyException, 
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, ClassNotFoundException, SQLException {
		//obtener texto de los fields
		String nombre = enterName.getText().strip();
		String mail = enterEmail.getText().strip();
		String contra = enterPassword.getText();
		//No acepta campos vacios
		if(!(mail.isEmpty() || contra.isBlank())) {
			if(!perspective) {//Iniciar sesion
				if(Queries.validSesion(mail, contra)) {
					error.setStyle("-fx-background-color: #91e291;-fx-border-color: #578857;-fx-background-radius: 9;-fx-border-radius: 9;");
					error.setAlignment(Pos.CENTER);
					error.setText("¡Ingreso Correctamente!");
					error.setVisible(true);
					cambiaVentanaPrincipal(event);
				} else {
					error.setVisible(true);
				}
			} 	
			else if(!nombre.isEmpty()) {
				Queries.createUser(nombre, mail, contra);
			}
			else{
				error.setStyle("-fx-background-color: #fcc0bf;-fx-border-color: #b12727;-fx-background-radius: 9;-fx-border-radius: 9;");
				error.setText("Debe llenar todos los campos");
				error.setVisible(true);
			}
		}
		else{
			error.setStyle("-fx-background-color: #fcc0bf;-fx-border-color: #b12727;-fx-background-radius: 9;-fx-border-radius: 9;");
			error.setText("Debe llenar todos los campos");
			error.setVisible(true);
		}
	}
	
	public void cambiaVentanaPrincipal(MouseEvent evento) throws IOException {
		rootPrinci = FXMLLoader.load(getClass().getResource("/source/PrincipalSceneOccupant.fxml"));
		ventanaPrinci=(Stage)((Node)evento.getSource()).getScene().getWindow();
		EscenarioPrinci=new Scene(rootPrinci);
		ventanaPrinci.setScene(EscenarioPrinci);
//		ventanaPrinci.setX(-10);
//		ventanaPrinci.setY(0);
		ventanaPrinci.setMaximized(true);
		ventanaPrinci.setResizable(false);
//		ventanaPrinci.showAndWait();
	}
	
}
