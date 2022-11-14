package connection;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Encrypter;
import dataClass.Vivienda;

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
		String insert = "INSERT INTO usuario"
				+ "(IDusuario, Nombre, Apellido, NombreUsuario, Contrasena, Correo, Estado, Fecha, MaxPorOfrecer, Salario, IDtipoUsuario, IDubicacion, IDagencia) "
				+ "VALUES(default, 'n', 'a', ?, ?, ?, default, default, null, null, ?, null, null)";
		DBConnection.createStatement(insert);
		DBConnection.getStatement().setString(1,username);
		DBConnection.getStatement().setString(2,Encrypter.encryptString(password));
		DBConnection.getStatement().setString(3,mail);
		DBConnection.getStatement().setInt(4, userType);// 1: cliente, 2: due�o, 3: empleado
		DBConnection.getStatement().executeUpdate();
		DBConnection.desconnect();
	}
	
	public static List<Vivienda> buscarPropiedades(List<String> paises, List<String> departamentos, List<String> municipios,
			String tipoPropiedad, Integer cantHabitaciones, Integer minRent, Integer maxRent) throws SQLException, ClassNotFoundException {
		DBConnection.connect();
		String query = "SELECT Vivienda.IDvivienda, TipoVivienda.tipo, Vivienda.Direccion, Vivienda.CantHabitaciones, Vivienda.PrecioRentaMensual, "
				+ "Vivienda.fecha, Vivienda.Descripcion, Ubicacion.pais "
				+ "FROM Vivienda "
				+ " JOIN Ubicacion ON Vivienda.IDubicacion = Ubicacion.IDubicacion "
				+ " JOIN TipoVivienda ON Vivienda.IDtipoViv = TipoVivienda.IDtipoViv "
				+ "WHERE Vivienda.Estado = 1";
		if (!(paises==null)) {
			query += " AND (Ubicacion.pais = '" + paises.get(0) + "'";
			for (int i=1; i<paises.size(); i++) {
				query += " OR Ubicacion.pais = '" + paises.get(i) + "'";
			}
			query += ")";
		}
		if (!(departamentos==null)) {
			query += " AND (Ubicacion.departamento = '" + departamentos.get(0) + "'";
			for (int i=1; i<departamentos.size(); i++) {
				query += " OR Ubicacion.departamento = '" + departamentos.get(i) + "'";
			}
			query += ")";
		}
		if (!(municipios==null)) {
			query += "AND (Ubicacion.municipio = '" + municipios.get(0)+ "'";
			for (int i=1; i<municipios.size(); i++) {
				query += " OR Ubicacion.municipio = '" + municipios.get(i) + "'";
			}
			query += ")";
		}
		if (!(tipoPropiedad==null)) {
			int tipo = 0;
			switch (tipoPropiedad) {
				case "Apartamento":
					tipo = 2;
				break;
				case "Casa":
					tipo = 1;
				break;
			}
			query += " AND TipoVivienda.tipo = " + tipo;
		}
		if (!(cantHabitaciones==null)) {
			query += " AND Vivienda.cantHabitaciones = " + cantHabitaciones;
		}
		if (!(minRent==null)) {
			query += " AND Vivienda.precioRentaMensual >= " + minRent;
		}
		if (!(maxRent==null)) {
			query += " AND Vivienda.precioRentaMensual <= " + maxRent;
		}
		DBConnection.createStatement(query);
		ResultSet rs = DBConnection.getStatement().executeQuery();
		List<Vivienda> viviendas = new ArrayList<>();
		while (rs.next()) {
			Integer ID = rs.getInt(1);
			String tipo = rs.getString(2);
			String direccion = rs.getString(3);
			Integer numHabitaciones = rs.getInt(4);
			Float rentaMensual = rs.getFloat(5);
			String fecha = rs.getString(6).substring(0,10);
			String descripcion = rs.getString(7);
			String pais = rs.getString(8);
			viviendas.add(new Vivienda(ID, tipo, direccion, numHabitaciones, rentaMensual, fecha, "Disponible", descripcion, pais));
		}
		DBConnection.desconnect();
		return viviendas;
	}
	
	public static ResultSet totPagadoDuenoPorPeriodo() throws SQLException {
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
}
