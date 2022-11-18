package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.Executors;

import javafx.scene.control.Alert;

public class DBConnection {
	private static Connection connection;
	private static PreparedStatement statement;
    private static String login = "is1270204";
    private static String password  ="qOqI64e0LT";
    private static String url = "jdbc:oracle:thin:@orion.javeriana.edu.co:1521/LAB";
    
    public static Connection getConnection() throws SQLException {
		return connection;
    }
    
    public static PreparedStatement getStatement() {
		return statement;
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        try {
            connection = DriverManager.getConnection(url, login, password);
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    
    public static void createStatement(String query) throws SQLException {
    	statement = connection.prepareStatement(query);
    }

    public static void desconnect() {
        try {
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Error al desconectar");
        }
    }
}
