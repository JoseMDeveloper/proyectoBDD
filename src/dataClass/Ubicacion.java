package dataClass;

public class Ubicacion {
	private int index;
	private Pais pais;
	private Departamento departamento;
	private Municipio municipio;
	
	public Ubicacion(int index, String pais, String departamento, String municipio) {
		this.index = index;
		this.pais = new Pais(pais);
		this.departamento = new Departamento(departamento);
		this.municipio = new Municipio(municipio);
		
		this.pais.addDepartamentos(this.departamento);
		this.departamento.setPais(this.pais);
		this.departamento.addMunicipios(this.municipio);
		this.municipio.setDepartamento(this.departamento);
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	@Override
	public String toString() {
		return "Pais: " + pais.getNombre() + ", Departamento: " + departamento.getNombre() + ", Municipio: " + municipio.getNombre();
	}
}