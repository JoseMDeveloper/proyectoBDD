package connection;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.Encrypter;
import dataClass.Factura;
import dataClass.Pago;
import dataClass.Sesion;
import dataClass.Ubicacion;
import dataClass.Usuario;
import dataClass.Visita;
import dataClass.Vivienda;
import dataClass.tipoPago;

public class Queries {
	public static boolean validSesion(String name, String password) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		DBConnection.connect();
		String query = "SELECT nombreUsuario, contrasena "
				+ "FROM usuario "
				+ "WHERE nombreUsuario=? AND contrasena=? AND Estado=1";
		DBConnection.createStatement(query);
		DBConnection.getStatement().setString(1,name);
		DBConnection.getStatement().setString(2,Encrypter.encryptString(password));
		ResultSet rs = DBConnection.getStatement().executeQuery();
		boolean valid = rs.next();
		DBConnection.desconnect();
		return valid;
	}
	
	public static Usuario getUser(String nombreUsuario) throws SQLException, ClassNotFoundException {
		DBConnection.connect();
		String query = "SELECT IDusuario, Nombre, Apellido, NombreUsuario, Contrasena, Correo, Estado, Fecha, MaxPorOfrecer, Salario, IDtipoUsuario, IDubicacion, IDagencia "
				+ "FROM usuario "
				+ "WHERE nombreUsuario=?";
		DBConnection.createStatement(query);
		DBConnection.getStatement().setString(1,nombreUsuario);
		ResultSet rs = DBConnection.getStatement().executeQuery();
		rs.next();
		Usuario user = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
				rs.getInt(7), rs.getString(8), rs.getFloat(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), null);
		DBConnection.desconnect();
		return user;
	}
	
	public static void createUser(String username, String mail, String password, int userType) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		DBConnection.connect();
		String insert = "INSERT INTO usuario"
				+ "(IDusuario, Nombre, Apellido, NombreUsuario, Contrasena, Correo, Estado, Fecha, MaxPorOfrecer, Salario, IDtipoUsuario, IDubicacion, IDagencia) "
				+ "VALUES(default, null, null, ?, ?, ?, default, default, ?, null, ?, null, null)";
		DBConnection.createStatement(insert);
		DBConnection.getStatement().setString(1,username);
		DBConnection.getStatement().setString(2,Encrypter.encryptString(password));
		DBConnection.getStatement().setString(3,mail);
		DBConnection.getStatement().setInt(4, 0);
		DBConnection.getStatement().setInt(5, userType);// 1: cliente, 2: dueno, 3: empleado
		DBConnection.getStatement().executeUpdate();
		DBConnection.desconnect();
	}
	
	public static void createpropi(String direccion, int CantHabitaciones, Float precio,String descripcion,int idubicacion,int tipoviv) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		DBConnection.connect();
		String insert = "INSERT INTO vivienda"
				+ "(IDvivienda, Direccion, CantHabitaciones, PrecioRentaMensual, Fecha, Estado, Descripcion, IDubicacion, IDtipoViv, IDAgencia)"
				+ "VALUES(default, ?, ?, ?,default,default,?,?,?,null)";
		DBConnection.createStatement(insert);
		DBConnection.getStatement().setString(1,direccion);
		DBConnection.getStatement().setInt(2,CantHabitaciones);
		DBConnection.getStatement().setFloat(3,precio);
		DBConnection.getStatement().setString(4, descripcion);
		DBConnection.getStatement().setInt(5, idubicacion);
		DBConnection.getStatement().setInt(6,tipoviv);
		DBConnection.getStatement().executeUpdate();
		DBConnection.desconnect();
	}
	
	public static void updateUser(String username, String mail, String password,String nombre, String Apellido, Float maximo) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		DBConnection.connect();
		String UPDATE = "UPDATE usuario"
					  + " set ";
		if(mail!=null) {
			UPDATE +="correo='"+mail+"',";
		}
		if(password!=null){
			UPDATE +="Contrasena='"+Encrypter.encryptString(password)+"',";	
		}
		if(nombre!=null){
			UPDATE +="Nombre='"+nombre+"',";	
		}
		if(Apellido!=null){
			UPDATE +="Apellido='"+Apellido+"',";	
		}
		if(maximo!=null){
			UPDATE +="MaxPorOfrecer="+maximo+",";	
		}
		UPDATE=UPDATE.substring(0,UPDATE.length()-1);
		UPDATE+="WHERE NombreUsuario='"+username+"'";
		DBConnection.createStatement(UPDATE);
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
		if (!(paises==null) && !(paises.isEmpty())) {
			query += " AND ((Ubicacion.pais = '" + paises.get(0) + "'";
			if (departamentos.get(0).equals("sin filtro")) {
				query += ")";
			}else {
				query += " AND Ubicacion.departamento = '" + departamentos.get(0) + "'";
				if (municipios.get(0).equals("sin filtro")) {
					query += ")";
				}else {
					query += " AND Ubicacion.municipio = '" + municipios.get(0)+ "')";
				}
			}
			for (int i=1; i<paises.size(); i++) {
				query += " OR (Ubicacion.pais = '" + paises.get(i) + "'";
				if (departamentos.get(i).equals("sin filtro")) {
					query += ")";
				}else {
					query += " AND Ubicacion.departamento = '" + departamentos.get(0) + "'";
					if (municipios.get(i).equals("sin filtro")) {
						query += ")";
					}else {
						query += " AND Ubicacion.municipio = '" + municipios.get(0)+ "')";
					}
				}
			}
			query += ")";
		}
		if (!(tipoPropiedad==null)) {
			query += " AND TipoVivienda.tipo = '" + tipoPropiedad+ "'";
		}
		if (!(cantHabitaciones==0)) {
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
	
	public static Map<Integer, Ubicacion> obtenerUbicacion() throws SQLException, ClassNotFoundException {
		DBConnection.connect();
		Map<Integer, Ubicacion> ubicaciones = new HashMap<>();
		String query = "SELECT IDubicacion, pais, departamento, municipio "
				+ "FROM ubicacion";
		DBConnection.createStatement(query);
		ResultSet rs = DBConnection.getStatement().executeQuery();
		while (rs.next()) {
			Ubicacion ubicacion = new Ubicacion(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4));
			ubicaciones.put(rs.getInt(1),ubicacion);
		}
		DBConnection.desconnect();
		return ubicaciones;
	}
	
	//de aqui pa abajo son nuevas
	public static void CrearVisita(Integer id, Integer visita) throws ClassNotFoundException, SQLException {
		DBConnection.connect();
		String crear="INSERT INTO visita"
		+ "(IDusuario, IDvivienda, Fecha) "
		+ "VALUES(?, ?,default)";
		DBConnection.createStatement(crear);
		DBConnection.getStatement().setInt(1,id);
		DBConnection.getStatement().setInt(2,visita);
		DBConnection.getStatement().executeUpdate();
		DBConnection.desconnect();
	}
	
	public static List<Visita> visitasCliente(Integer ID) throws ClassNotFoundException, SQLException{
		DBConnection.connect();
		String consulta="SELECT visita.IDvivienda, visita.IDusuario, visita.fecha "
				+ "FROM Visita"
				+ " JOIN Vivienda ON Visita.IDvivienda=Vivienda.IDvivienda "
				+ "WHERE visita.IDusuario=? AND visita.fecha>sysdate";
		List<Visita> visitas = new ArrayList<>();
		DBConnection.createStatement(consulta);
		DBConnection.getStatement().setInt(1,ID);
		ResultSet res = DBConnection.getStatement().executeQuery();
		while (res.next()) {
			Integer IDvivienda = res.getInt(1);
			Integer IDusuario = res.getInt(2);
			String fecha = res.getString(3).substring(0,10);;
			
			visitas.add(new Visita(IDvivienda, IDusuario, fecha));
		}
		DBConnection.desconnect();
		return visitas;
	}
	
	public static List<Visita> historialVisitasCliente(Integer ID) throws ClassNotFoundException, SQLException{
		DBConnection.connect();
		String consulta="SELECT visita.IDvivienda, visita.IDusuario, visita.fecha "
				+ "FROM Visita"
				+ " JOIN Vivienda ON Visita.IDvivienda=Vivienda.IDvivienda "
				+ "WHERE visita.IDusuario=? AND visita.fecha<=sysdate";
		List<Visita> visitas = new ArrayList<>();
		DBConnection.createStatement(consulta);
		DBConnection.getStatement().setInt(1,ID);
		ResultSet res = DBConnection.getStatement().executeQuery();
		while (res.next()) {
			Integer IDvivienda = res.getInt(1);
			Integer IDusuario = res.getInt(2);
			String fecha = res.getString(3).substring(0,10);
			
			visitas.add(new Visita(IDvivienda, IDusuario, fecha));
		}
		DBConnection.desconnect();
		return visitas;
	}
	
	public static void eliminarCuenta() throws SQLException, ClassNotFoundException {
		DBConnection.connect();
		String update = "UPDATE usuario"
				+ " SET estado=0"
				+ " WHERE IDusuario='"+Sesion.getUser().getId()+"'";
		DBConnection.createStatement(update);
		DBConnection.getStatement().executeQuery();
		DBConnection.desconnect();
	}
	public static void eliminarpropi(Integer id) throws SQLException, ClassNotFoundException {
		DBConnection.connect();
		String update = "UPDATE vivienda"
				+ " SET estado=0"
				+ " WHERE IDvivienda='"+id+"'";
		DBConnection.createStatement(update);
		DBConnection.getStatement().executeQuery();
		DBConnection.desconnect();
	}
	
	public static boolean visitaUsuarioVivienda(Integer idUsuario, Integer idVivienda) throws SQLException, ClassNotFoundException {
		DBConnection.connect();
		String query = "SELECT idUsuario, idVivienda "
				+ "FROM visita "
				+ "WHERE idUsuario=? AND idVivienda=?";
		DBConnection.createStatement(query);
		DBConnection.getStatement().setInt(1,idUsuario);
		DBConnection.getStatement().setInt(2,idVivienda);
		ResultSet rs = DBConnection.getStatement().executeQuery();
		boolean valid = rs.next();
		DBConnection.desconnect();
		return valid;
	}
	
	public static List<Vivienda> viviendasPropietario(Integer id) throws ClassNotFoundException, SQLException{
		DBConnection.connect();
		String consulta="SELECT Vivienda.IDvivienda, TipoVivienda.tipo, Vivienda.Direccion, Vivienda.CantHabitaciones, "
				+ "Vivienda.PrecioRentaMensual, Vivienda.fecha, Vivienda.Descripcion, Ubicacion.pais, Vivienda.estado "
				+ "FROM Vivienda "
				+ " JOIN Ubicacion ON Vivienda.IDubicacion = Ubicacion.IDubicacion "
				+ " JOIN TipoVivienda ON Vivienda.IDtipoViv = TipoVivienda.IDtipoViv "
				+ " JOIN Factura ON Vivienda.IDvivienda = Factura.IDvivienda "
				+ " JOIN Usuario ON Factura.IDusuario = Usuario.IDusuario"
				+ "WHERE IDusuario=? AND NOT vivienda.estado=0";
		List<Vivienda> vivs = new ArrayList<>();
		DBConnection.createStatement(consulta);
		DBConnection.getStatement().setInt(1,id);
		ResultSet rs = DBConnection.getStatement().executeQuery();
		while (rs.next()) {
			Integer ID = rs.getInt(1);
			String tipo = rs.getString(2);
			String direccion = rs.getString(3);
			Integer numHabitaciones = rs.getInt(4);
			Float rentaMensual = rs.getFloat(5);
			String fecha = rs.getString(6).substring(0,10);
			String descripcion = rs.getString(7);
			String pais = rs.getString(8);
			String estado;
			if(rs.getInt(9)==1) {
				estado = "Disponible";
			}else {
				estado = "Arrendada";
			}
			vivs.add(new Vivienda(ID, tipo, direccion, numHabitaciones, rentaMensual, fecha, estado, descripcion, pais));
		}
		DBConnection.desconnect();
		return vivs;
	}
	
	public void insertTransaccionPago(Integer idus, Integer idviv, Factura f, List<tipoPago> tps) throws ClassNotFoundException, SQLException {
		DBConnection.connect();
		DBConnection.getConnection().setAutoCommit(false);
		String code = "insert into Factura values(default,'"+f.getCorreo()+"',"+f.getTotal()+", sysdate,null,"+idus+","+idviv+");";
		DBConnection.createStatement(code);
		DBConnection.getStatement().executeQuery();
		code = "select max(idfactura) from Factura";
		DBConnection.createStatement(code);
		ResultSet rs = DBConnection.getStatement().executeQuery();
		rs.next();
		Integer idNewFactura = rs.getInt(1);
		for (tipoPago tp : tps) {
			if (tp.getTipPago().equals("Efectivo")) {
				code = "insert into pago values ("+idNewFactura+","+4+",null,"+tp.getMonto()+",null,null,null,null)";
				DBConnection.createStatement(code);
				DBConnection.getStatement().executeQuery();
			}else if (tp.getTipPago().equals("Bono")) {
				code = "insert into pago values ("+idNewFactura+","+3+",null,"+tp.getMonto()+","+tp.getNumero()+",null,null,null)";
				DBConnection.createStatement(code);
				DBConnection.getStatement().executeQuery();
			}else if (tp.getTipPago().equals("Tarjeta")) {
				code = "insert into pago values ("+idNewFactura+",null, null,"+tp.getMonto()+","+tp.getNumero()+",null,null,null)";
				DBConnection.createStatement(code);
				DBConnection.getStatement().executeQuery();
			}
		}
		DBConnection.getConnection().commit();
		DBConnection.getConnection().setAutoCommit(true);
		DBConnection.desconnect();
	}
}
