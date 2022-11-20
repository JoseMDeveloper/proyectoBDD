package dataClass;

public class Visita {
	private Integer IDusuario;
	private Integer IDvivienda;
	private String fecha;
	
	public Visita(Integer iDusuario, Integer iDvivienda, String fecha) {
		IDusuario = iDusuario;
		IDvivienda = iDvivienda;
		this.fecha = fecha;
	}
	public Integer getIDusuario() {
		return IDusuario;
	}
	public void setIDusuario(Integer iDusuario) {
		IDusuario = iDusuario;
	}
	public Integer getIDvivienda() {
		return IDvivienda;
	}
	public void setIDvivienda(Integer iDvivienda) {
		IDvivienda = iDvivienda;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
