package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;
    private static String login = "is1270204";
    private static String password  ="qOqI64e0LT";
    private static String url = "jdbc:oracle:thin:@orion.javeriana.edu.co:1521/LAB";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            connection = DriverManager.getConnection(url, login, password);
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
        return connection;
    }

    public static void desconectar() {
        try {
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Error al desconectar");
        }
    }
}
