package application;

import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.BorderPane;

public class RegisterControllerController implements Initializable{
	private boolean bo1=false;
	
	
	@FXML
	private BorderPane pantalla1;
	@FXML
	private AnchorPane ancla2;
	@FXML
	private TextField ingresarN;
	@FXML
	private TextField ingresarE;
	@FXML
	private TextField ingresarC;
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
	}
	@FXML
	public void registrar(MouseEvent event)
	{
		if(bo1==false)
		{	
			
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
				slide. setOnFinished((e->{}));
				bo1=true;
			
				boton1.setVisible(false);
				boton2.setVisible(true);
				boton3.setText("Registrar");
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
			slide. setDuration (Duration.seconds(0.7));
			slide. setNode(ancla1);
			slide.setByX(-325);
			slide.play();
			
			ancla2.setTranslateX(0);
			slide. setOnFinished((e->{}));
			bo1=false;
			
			boton1.setVisible(true);
			boton2.setVisible(false);
			boton3.setText("Registrar");
		}
	}
}
