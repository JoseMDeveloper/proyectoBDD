package dataClass;

import java.util.ArrayList;
import java.util.List;

public class Pais {
	private String nombre;
	private List<Departamento> departamentos = new ArrayList<>();
	
	public Pais(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void addDepartamentos(Departamento departamento) {
		this.departamentos.add(departamento);
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
