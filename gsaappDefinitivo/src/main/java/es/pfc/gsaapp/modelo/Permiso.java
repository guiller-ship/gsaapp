package es.pfc.gsaapp.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "permisos")
public class Permiso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "La fecha de inicio no debe ser nula")
	@Column(name = "fechaInicio")
	private LocalDate fechaInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "La fecha de fin no debe ser nula")
	@Column(name = "fechaFin")
	private LocalDate fechaFin;

	@NotNull(message = "El tipo de permiso no debe ser nulo")
	@Column(name = "tipoPermiso")
    private String tipoPermiso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

    // Constructores, getters y setters

    public Permiso() {
        // Constructor por defecto
    }

    public Permiso(Long id, LocalDate fechaInicio, LocalDate fechaFin, String tipoPermiso) {
    	this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoPermiso = tipoPermiso;
    }
    
    public Permiso(LocalDate fechaInicio, LocalDate fechaFin, String tipoPermiso) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoPermiso = tipoPermiso;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipoPermiso() {
        return tipoPermiso;
    }

    public void setTipoPermiso(String tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}