package connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {
	public static ResultSet totPropiedadesPorClientePorPeriodo() throws SQLException {
		//Cantidad Total de propiedades rentadas por cliente por períodos (mes, año)
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet totPagadoDuenoPorPeriodo() throws SQLException {
		//Suma total pagada a los dueños por las rentas de sus propiedades (suma del valor de renta estipulado) por períodos
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}

	public static ResultSet totRentasPorClientePorPeriodo() throws SQLException {
		//Suma y cantidad de rentas por cliente, por período
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet totRentasPorUbicacion() throws SQLException {
		//Número total de rentas por país, departamento, municipio y ubicación (location)
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet impuestosAPagarPorRentaPorPeriodo() throws SQLException {
		//Reporte por periodos (año, mes) de Impuestos que se deben pagar por cada renta
		String query = "";
		return DBConnection.getStatement().executeQuery(query);
	}
	
	public static ResultSet topPropiedadesRentadas() throws SQLException {
		//¿Cuáles son los tipos de propiedades más rentadas?
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
