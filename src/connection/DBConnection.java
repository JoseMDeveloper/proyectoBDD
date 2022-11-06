package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static Connection connection;
	private static Statement statement;
    private static String login = "is1270204";
    private static String password  ="qOqI64e0LT";
    private static String url = "jdbc:oracle:thin:@orion.javeriana.edu.co:1521/LAB";
    
    public static Connection getConnection() {
		return connection;
    }
    
    public static Statement getStatement() {
		return statement;
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        try {
            connection = DriverManager.getConnection(url, login, password);
            statement = connection.createStatement();
            //conn.setAutoCommit(false);
            if(connection != null) {
                System.out.println("Conexion exitosa");
            }
            else {
                System.out.println("Conexion erronea");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Error al desconectar");
        }
    }
}
