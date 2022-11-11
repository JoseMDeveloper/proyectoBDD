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
		boolean valid;
		valid = rs.next();
		DBConnection.desconnect();
		return valid;
	}
	
	public static void createUser(String username, String mail, String password) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		DBConnection.connect();
		String insert = "INSERT INTO usuario VALUES(null, 'n', 'a', 0, ?, ?, ?, null, 1, 1, null)";// 1: cliente, 2: dueño, 3: empleado, 4: admin
		DBConnection.createStatement(insert);
		DBConnection.getStatement().setString(1,username);
		DBConnection.getStatement().setString(2,Encrypter.encryptString(password));
		DBConnection.getStatement().setString(3,mail);
		DBConnection.getStatement().executeUpdate();
		DBConnection.desconnect();
	}
	
	public static ResultSet buscarPropiedades(List<Integer> listUbicaciones, String tipoPropiedad, int cantHabitaciones, int minRent, int maxRent)
			throws SQLException {
		String query = "SELECT TipoVivienda.tipo, Vivienda.Direccion, Vivienda.CantHabitaciones, Vivienda.PrecioRentaMensual, "
				+ "ViviendaDescripcion, Ubicacion.pais "
				+ "FROM Vivienda "
				+ " JOIN Ubicacion ON Vivienda.IDubicacion = Ubicacion.IDubicacion "
				+ " JOIN TipoVivienda ON Vivienda.IDtipoViv = TipoVivienda.IDtipoViv ";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet totPagadoDuenoPorPeriodo() throws SQLException {
		//Suma total pagada a los dueños por las rentas de sus propiedades (suma del valor de renta estipulado) por periodos
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}

	public static ResultSet totRentasPorClientePorPeriodo() throws SQLException {
		//Suma y cantidad de rentas por cliente, por periodo
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet totRentasPorUbicacion() throws SQLException {
		//Numero total de rentas por pais, departamento, municipio y ubicacion (location)
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet impuestosAPagarPorRentaPorPeriodo() throws SQLException {
		//Reporte por periodos (año, mes) de Impuestos que se deben pagar por cada renta
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet topPropiedadesRentadas() throws SQLException {
		//ï¿½Cuï¿½les son los tipos de propiedades mas rentadas?
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet totRentasConImpuestosPorPeriodo() throws SQLException {
		//Totales de rentas por periodo con impuestos
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet totComisionesPagadasPorPeriodos() throws SQLException {
		//Totales de comisiones pagadas por periodo
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
}
