package es.pfc.gsaapp.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.pfc.gsaapp.modelo.Usuario;

public interface UsuarioPaginadorRepositorio extends PagingAndSortingRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.nombre = 'ROLE_USER'")
    public Page<Usuario> listarUsuariosPorRolUser(Pageable pageable);
	
}
