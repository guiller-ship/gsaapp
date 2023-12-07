package es.pfc.gsaapp.modelo;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import es.pfc.gsaapp.modelo.tipos.EstadoPermiso;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "username"}))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	private String username;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "cargo")
	private String cargo;
	
	@Column(name = "antiguedad")
	private String antiguedad;
	
	@Column(name = "avatar")
	private String avatar;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
			)
	@Column(name = "rol")
	private Collection<Rol> roles;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<Permiso> permisos;

	private boolean permisosDenegados;

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	public Set<Permiso> getPermisos() {
		return permisos;
	}
	
	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}
	
	public boolean hasPermisoPendiente() {
	    return this.getPermisos().stream().anyMatch(permiso -> permiso.getEstado() == EstadoPermiso.PENDIENTE);
	}

	public Usuario(Long id, String username, String nombre, String apellidos, String email, String password, String cargo, 
			String antiguedad, String avatar, Collection<Rol> roles, Set<Permiso> permisos) {
		super();
		this.id = id;
		this.username = username;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.cargo = cargo;
		this.antiguedad = antiguedad;
		this.avatar = avatar;
		this.roles = roles;
		this.permisos = permisos;
	}

	public Usuario(String nombre, String username, String apellidos, String email, String password, String cargo, 
			String antiguedad, String avatar, Collection<Rol> roles, Set<Permiso> permisos) {
		super();
		this.username = username;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.cargo = cargo;
		this.antiguedad = antiguedad;
		this.avatar = avatar;
		this.roles = roles;
		this.permisos = permisos;
	}

	public Usuario() {}
}
