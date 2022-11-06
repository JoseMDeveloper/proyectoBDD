package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javafx.util.Duration;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.BorderPane;

public class RegisterControllerController implements Initializable{
	//Map contiene <mail, contraseÃ±a>
	HashMap<String, String> cuentas = new HashMap<>();
	Encryptor encriptador = new Encryptor();
	private boolean bo1=false;
	private String bo3="iniciar sesion";
	
	 File file = new File("data.csv");
	 
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
	private TextField ingresarN;
	@FXML
	private TextField ingresarE;
	@FXML
	private PasswordField ingresarC;
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
	
	@Override
 	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ingresarN.setVisible(false);
		ingresarE.setTranslateY(-35);
		ingresarC.setTranslateY(-20);
		seTeOlvido.setTranslateY(-20);
		boton2.setVisible(false);
		profe.setVisible(false);
		error.setVisible(false);
	}
	
	@FXML
	public void registrar(MouseEvent event) {
		if(bo1==false) {	
			ingresarN.setVisible(true);
			seTeOlvido.setVisible(false);
			if(ingresarE.getTranslateY()<=35)
			{
				ingresarE.setTranslateY(10);
				ingresarC.setTranslateY(20);
			}
				TranslateTransition slide = new TranslateTransition();
				slide. setDuration (Duration. seconds(0.7));
				slide. setNode(ancla1);
				slide.setByX(325);
				slide.play();
				
				ancla2.setTranslateX(-415);
				slide.setOnFinished((e->{}));
				bo1=true;
			
				boton1.setVisible(false);
				boton2.setVisible(true);
				boton3.setText("Registrar");
				bo3="Registrar";
				recibir.setText("¡Bienvenido!");
				profe.setVisible(true);
				texto.setText("Para crear una nueva cuenta, ingresa tu nombre, correo electronico y establece una contraseña.");
				slogan.setText("Los mejores profesionales a tu servicio, Tu casa en buenas manos");
				error.setVisible(false);
		}
	}
	public void iniciarSesion(MouseEvent event)
	{
		
		if(bo1==true)
		{	
			seTeOlvido.setVisible(true);
			if(ingresarE.getLayoutX()>=52.8)
			{
				ingresarE.setTranslateY(-30);
				ingresarC.setTranslateY(-20);
				seTeOlvido.setTranslateY(-20);
			}
			ingresarN.setVisible(false);
			TranslateTransition slide = new TranslateTransition();
			slide.setDuration (Duration.seconds(0.7));
			slide.setNode(ancla1);
			slide.setByX(-325);
			slide.play();
			
			ancla2.setTranslateX(0);
			slide.setOnFinished((e->{}));
			bo1=false;
			
			boton1.setVisible(true);
			boton2.setVisible(false);
			boton3.setText("iniciar sesion");
			bo3="iniciar sesion";
			recibir.setText("¡Hola de Nuevo!");
			profe.setVisible(false);
			texto.setText("Para iniciar sesion en tu cuenta, ingrese su direccion de correo electronico y su contraseña.");
			slogan.setText("No buscamos tu piso, encontramos tu hogar, y si quieres hacer parte de esta familia dale registrar");
			error.setVisible(false);
		}
	}

	public void siguiente(MouseEvent event) throws NoSuchAlgorithmException, IOException, InvalidKeyException, 
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		//Ingresar 
		String nombre=ingresarN.getText();
		String mail=ingresarE.getText();
		String contra=ingresarC.getText();	
		if(bo3.equals("iniciar sesion")){
			actualizarUsuarioyContra();
			String contraencrip=cuentas.get(mail);
			System.out.println(contraencrip);
			System.out.println(contra);
			if(encriptador.encryptString(contra).equals(contraencrip)){
	            System.out.println("Ingreso Correctamente!");
	            error.setText("Funciono");
	            error.setVisible(true);
	        } else {
	        	error.setText("aaaaa");
	            error.setVisible(true);
	        }
		}
		if(bo3.equals("Registrar")){
			crearcuenta(nombre,mail,contra);
		}
		//Crear cuenta
	}
	
	public void actualizarUsuarioyContra() throws IOException {
        Scanner scaner = new Scanner(file);
        cuentas.clear();
        cuentas = new HashMap<>();
        while (scaner.hasNext()){
            String[] usuarioycontra = scaner.nextLine().split(",");
            cuentas.put(usuarioycontra[1],usuarioycontra[2]);
            System.out.println("mail: "+ usuarioycontra[1] + " | contra: "+ usuarioycontra[2]);
        }
        scaner.close();
    }
	
	public void crearcuenta(String nombre,String mail, String contra) throws IOException, NoSuchPaddingException,
			InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));

        writer.write(nombre + ","+ mail + "," + encriptador.encryptString(contra) + "\n");
        writer.close();
	}
	
}
