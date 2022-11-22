package dataClass;

public class Vivienda {
	@Override
	public String toString() {
		return "Vivienda [id=" + id + ", tipo=" + tipo + ", direccion=" + direccion + ", cantHabitaciones="
				+ cantHabitaciones + ", precioRentaMensual=" + precioRentaMensual + ", fecha=" + fecha + ", estado="
				+ estado + ", descripcion=" + descripcion + ", pais=" + pais + ", departamento=" + departamento
				+ ", municipio=" + municipio + "]";
	}

	private Integer id;
	private String tipo;
	private String direccion;
	private Integer cantHabitaciones;
	private Float precioRentaMensual;
	private String fecha;
	private String estado;
	private String descripcion;
	private String pais;
	private String departamento;
	public String getDepartamento() {
		return departamento;
	}

	private String municipio;
	
	public Vivienda(Integer id, String tipo, String direccion, Integer cantHabitaciones, Float precioRentaMensual, String fecha, String estado,
			String descripcion, String pais) {
		this.id = id;
		this.tipo = tipo;
		this.direccion = direccion;
		this.cantHabitaciones = cantHabitaciones;
		this.precioRentaMensual = precioRentaMensual;
		this.fecha = fecha;
		this.estado = estado;
		if (descripcion ==null || descripcion.isBlank()) {
			this.descripcion = "Sin descripcion";
		}else {
			this.descripcion = descripcion;
		}
		this.pais = pais;
	}
	
	public Integer getId() {
		return id;
	}

	public String getDireccion() {
		return direccion;
	}

	public Float getPrecioRentaMensual() {
		return precioRentaMensual;
	}

	public Integer getCantHabitaciones() {
		return cantHabitaciones;
	}

	public String getFecha() {
		return fecha;
	}

	public String getEstado() {
		return estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getPais() {
		return pais;
	}

	public String getTipo() {
		return tipo;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setCantHabitaciones(Integer cantHabitaciones) {
		this.cantHabitaciones = cantHabitaciones;
	}

	public void setPrecioRentaMensual(Float precioRentaMensual) {
		this.precioRentaMensual = precioRentaMensual;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}