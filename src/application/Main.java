package application;
	
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.util.Scanner;


public class Main extends Application {
    public void start(Stage stage) throws Exception{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/source/RegisterController.fxml"));
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
//			stage.initStyle(StageStyle.UNDECORATED);
//			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setResizable(false);
			stage.setTitle("VAMOSSS");
			stage.setScene(scene);
			stage.show();
			Scanner scanner = new Scanner(System.in);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}
