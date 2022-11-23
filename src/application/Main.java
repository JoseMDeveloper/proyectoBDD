package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

//Admin teamo (empleado)
//cliente0 teamo (cliente)
//propietario0 teamo (propietario)

public class Main extends Application {
	
    public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/source/LoginScene.fxml"));
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
//			stage.initStyle(StageStyle.UNDECORATED);
//			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setResizable(false);
			stage.setTitle("La Casa De Tus Sue�os");
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }
}
