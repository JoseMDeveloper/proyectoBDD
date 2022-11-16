package dataClass;
public class Factura {
	private Integer id;
	private String Correo;
	private Float total;
	private String Fechainicio;
	private String FechaFin;
	private Integer Idusuario;
	private Integer Idvivienda;
	private Integer Idservicio;
	public Factura(Integer id, String correo, Float total, String fechainicio, String fechaFin, Integer idusuario,
			Integer idvivienda, Integer idservicio) {
		this.id = id;
		Correo = correo;
		this.total = total;
		Fechainicio = fechainicio;
		FechaFin = fechaFin;
		Idusuario = idusuario;
		Idvivienda = idvivienda;
		Idservicio = idservicio;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCorreo() {
		return Correo;
	}
	public void setCorreo(String correo) {
		Correo = correo;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public String getFechainicio() {
		return Fechainicio;
	}
	public void setFechainicio(String fechainicio) {
		Fechainicio = fechainicio;
	}
	public String getFechaFin() {
		return FechaFin;
	}
	public void setFechaFin(String fechaFin) {
		FechaFin = fechaFin;
	}
	public Integer getIdusuario() {
		return Idusuario;
	}
	public void setIdusuario(Integer idusuario) {
		Idusuario = idusuario;
	}
	public Integer getIdvivienda() {
		return Idvivienda;
	}
	public void setIdvivienda(Integer idvivienda) {
		Idvivienda = idvivienda;
	}
	public Integer getIdservicio() {
		return Idservicio;
	}
	public void setIdservicio(Integer idservicio) {
		Idservicio = idservicio;
	}
	
	
	
	
}
