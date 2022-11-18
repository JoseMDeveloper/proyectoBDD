package dataClass;
public abstract class Usuario {
	private Integer Id;
	private String Nombre;
	private String Apellido;
	private String Nombredeusuario;
	private String Contrasena;
	private String Correo;
	private Integer Estado;
	private String Fecha;
	private Integer Maximo;
	private Integer Salario;
	private Integer IDtipousuario;
	private Integer IDubicacion;
	private Integer IDagencia;
	
	public Usuario(Integer id, String nombre, String apellido, String nombredeusuario, String contrasena, String correo,
			Integer estado, String fecha, Integer maximo, Integer salario, Integer iDtipousuario, Integer iDubicacion,
			Integer iDagencia) {
		Id = id;
		Nombre = nombre;
		Apellido = apellido;
		Nombredeusuario = nombredeusuario;
		Contrasena = contrasena;
		Correo = correo;
		Estado = estado;
		Fecha = fecha;
		Maximo = maximo;
		Salario = salario;
		IDtipousuario = iDtipousuario;
		IDubicacion = iDubicacion;
		IDagencia = iDagencia;
	}
	
	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getNombredeusuario() {
		return Nombredeusuario;
	}
	public void setNombredeusuario(String nombredeusuario) {
		Nombredeusuario = nombredeusuario;
	}
	public String getContrasena() {
		return Contrasena;
	}
	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}
	public String getCorreo() {
		return Correo;
	}
	public void setCorreo(String correo) {
		Correo = correo;
	}
	public Integer getEstado() {
		return Estado;
	}
	public void setEstado(Integer estado) {
		Estado = estado;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public Integer getMaximo() {
		return Maximo;
	}
	public void setMaximo(Integer maximo) {
		Maximo = maximo;
	}
	public Integer getSalario() {
		return Salario;
	}
	public void setSalario(Integer salario) {
		Salario = salario;
	}
	public Integer getIDtipousuario() {
		return IDtipousuario;
	}
	public void setIDtipousuario(Integer iDtipousuario) {
		IDtipousuario = iDtipousuario;
	}
	public Integer getIDubicacion() {
		return IDubicacion;
	}
	public void setIDubicacion(Integer iDubicacion) {
		IDubicacion = iDubicacion;
	}
	public Integer getIDagencia() {
		return IDagencia;
	}
	public void setIDagencia(Integer iDagencia) {
		IDagencia = iDagencia;
	}
	
	
}
