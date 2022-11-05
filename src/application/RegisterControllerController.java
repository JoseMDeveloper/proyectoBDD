package application;

import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.BorderPane;

public class RegisterControllerController implements Initializable{
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
	private Button boton2;
	@FXML
	private AnchorPane ancla1;
	@FXML
	private Button boton1;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	public void btn(MouseEvent event)
	{
		TranslateTransition slide = new TranslateTransition();
		slide. setDuration (Duration. seconds(0.7));
		slide. setNode(ancla1);
		slide.setByX(325);
		slide.play();
		
		ancla2.setTranslateX(-415);
		slide. setOnFinished((e->{}));
	}
	public void btn1(MouseEvent event)
	{
		TranslateTransition slide = new TranslateTransition();
		slide. setDuration (Duration.seconds(0.7));
		slide. setNode(ancla1);
		slide.setByX(-325);
		slide.play();
		
		ancla2.setTranslateX(0);
		slide. setOnFinished((e->{}));
	}
}
