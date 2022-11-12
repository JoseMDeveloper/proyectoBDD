package connection;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import application.Encrypter;

public class Queries {
	public static boolean validSesion(String mail, String password) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		DBConnection.connect();
		String query = "SELECT correo, contrasena "
				+ "FROM usuario "
				+ "WHERE correo=? AND contrasena=?";
		DBConnection.createStatement(query);
		DBConnection.getStatement().setString(1,mail);
		DBConnection.getStatement().setString(2,Encrypter.encryptString(password));
		ResultSet rs = DBConnection.getStatement().executeQuery();
		boolean valid = rs.next();
		DBConnection.desconnect();
		return valid;
	}
	
	public static void createUser(String username, String mail, String password, int userType) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		DBConnection.connect();
		String insert = "INSERT INTO usuario VALUES(default, 'n', 'a', ?, ?, ?, default, default, null, null, ?, null, null)";// 1: cliente, 2: dueño, 3: empleado, 4: admin
		DBConnection.createStatement(insert);
		DBConnection.getStatement().setString(1,username);
		DBConnection.getStatement().setString(2,Encrypter.encryptString(password));
		DBConnection.getStatement().setString(3,mail);
		DBConnection.getStatement().setInt(4, userType);
		DBConnection.getStatement().executeUpdate();
		DBConnection.desconnect();
	}
	
	public static ResultSet buscarPropiedades(List<Integer> listUbicaciones, String tipoPropiedad, int cantHabitaciones, int minRent, int maxRent)
			throws SQLException {
		String query = "SELECT TipoVivienda.tipo, Vivienda.Direccion, Vivienda.CantHabitaciones, Vivienda.PrecioRentaMensual, "
				+ "ViviendaDescripcion, Ubicacion.pais "
				+ "FROM Vivienda "
				+ " JOIN Ubicacion ON Vivienda.IDubicacion = Ubicacion.IDubicacion "
				+ " JOIN TipoVivienda ON Vivienda.IDtipoViv = TipoVivienda.IDtipoViv "
				+ "WHERE ";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet totPagadoDuenoPorPeriodo() throws SQLException {
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
}
