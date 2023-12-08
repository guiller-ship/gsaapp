package es.pfc.gsaapp.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.pfc.gsaapp.modelo.Permiso;

@Repository
public interface PermisoRepositorio extends JpaRepository<Permiso, Long> {

	@Query("SELECT p FROM Permiso p WHERE p.tipoPermiso = :tipoPermiso")
    Permiso findByTipoPermiso(String tipoPermiso);
}