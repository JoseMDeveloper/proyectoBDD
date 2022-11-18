package dataClass;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
	private String nombre;
	private Pais pais;
	private List<Municipio> municipios = new ArrayList<>();
	
	public Departamento(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void addMunicipios(Municipio municipios) {
		this.municipios.add(municipios);
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o!=null) {			
			return nombre.equals(o.toString());
		}
		return false;
	}
}
