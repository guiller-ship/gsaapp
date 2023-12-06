package es.pfc.gsaapp.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.pfc.gsaapp.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	public Usuario findByEmail(String email);

	@Query("SELECT u FROM Usuario u WHERE u.username = :username")
	public Usuario findByUsername(String username);
}