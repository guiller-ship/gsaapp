package es.pfc.gsaapp.servicio;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import es.pfc.gsaapp.controlador.dto.UsuarioDTO;
import es.pfc.gsaapp.modelo.Usuario;

public interface UsuarioServicio extends UserDetailsService {
	
//	public List<Usuario> listarUsuarios();
	
	public Page<Usuario> listarUsuariosPorRolUser(Pageable pageable);

	public Usuario findByEmail(String email);
	
	public Usuario findByUsername(String username);
	
	public Optional<Usuario> findById(Long idUsuario);

	public Usuario guardar(UsuarioDTO registroDTO);
	
	public Usuario obtenerDetallesUsuarioPorId(Long id);

	public boolean denegarPermisos(Long usuarioId);

	public void aceptarPermisos(Long usuarioId);
}
