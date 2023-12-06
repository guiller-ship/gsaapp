package es.pfc.gsaapp.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.pfc.gsaapp.modelo.Permiso;

public interface PermisoRepositorio extends JpaRepository<Permiso, Long> {

	@Query("SELECT p FROM Permiso p WHERE p.tipoPermiso = :tipoPermiso")
    Permiso findByTipoPermiso(String tipoPermiso);
}