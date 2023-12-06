package es.pfc.gsaapp.controlador.dto;

public class UsuarioDTO {

	private Long id;
	private String username;
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	private String cargo;
	private String antiguedad;
	private String rol;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public UsuarioDTO(Long id, String username, String nombre, String apellidos, String email, String password, 
			String cargo, String antiguedad, String rol) {
		super();
		this.id = id;
		this.username = username;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.cargo = cargo;
		this.antiguedad = antiguedad;
		this.rol = rol;
	}

	public UsuarioDTO(String username, String nombre, String apellidos, String email, String password, 
			String cargo, String antiguedad, String rol) {
		super();
		this.username = username;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.cargo = cargo;
		this.antiguedad = antiguedad;
		this.rol = rol;
	}

	public UsuarioDTO() {}
}
