package connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {
	public static ResultSet totPropiedadesPorClientePorPeriodo() throws SQLException {
		//Cantidad Total de propiedades rentadas por cliente por per�odos (mes, a�o)
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet totPagadoDuenoPorPeriodo() throws SQLException {
		//Suma total pagada a los due�os por las rentas de sus propiedades (suma del valor de renta estipulado) por per�odos
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}

	public static ResultSet totRentasPorClientePorPeriodo() throws SQLException {
		//Suma y cantidad de rentas por cliente, por per�odo
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet totRentasPorUbicacion() throws SQLException {
		//N�mero total de rentas por pa�s, departamento, municipio y ubicaci�n (location)
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet impuestosAPagarPorRentaPorPeriodo() throws SQLException {
		//Reporte por periodos (a�o, mes) de Impuestos que se deben pagar por cada renta
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet topPropiedadesRentadas() throws SQLException {
		//�Cu�les son los tipos de propiedades m�s rentadas?
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
