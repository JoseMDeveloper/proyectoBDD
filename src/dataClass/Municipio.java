package dataClass;

public class Municipio {
	private String nombre;
	private Departamento departamento;
	
	public Municipio(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	@Override
	public boolean equals(Object o) {
		return nombre.equals(o.toString());
	}
}
