package dataClass;

public class Vivienda {
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
}