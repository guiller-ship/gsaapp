package es.pfc.gsaapp.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.pfc.gsaapp.controlador.dto.UsuarioDTO;
import es.pfc.gsaapp.modelo.Permiso;
import es.pfc.gsaapp.modelo.Rol;
import es.pfc.gsaapp.modelo.Usuario;
import es.pfc.gsaapp.modelo.tipos.EstadoPermiso;
import es.pfc.gsaapp.repositorio.PermisoRepositorio;
import es.pfc.gsaapp.repositorio.UsuarioPaginadorRepositorio;
import es.pfc.gsaapp.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private PermisoRepositorio permisoRepositorio;
	
	@Autowired
	private UsuarioPaginadorRepositorio usuarioPaginadorRepositorio;
	
	@Override
	public Usuario guardar(UsuarioDTO registroDTO) {
		Usuario usuario = new Usuario(registroDTO.getUsername(), registroDTO.getNombre(), 
				registroDTO.getApellidos(), registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()), registroDTO.getCargo(), 
				registroDTO.getAntiguedad(), null, Arrays.asList(new Rol(registroDTO.getRol())), null);
		
		return usuarioRepositorio.save(usuario);
	}
	
//	@Override
//	public List<Usuario> listarUsuarios() {
//		return usuarioRepositorio.findAll();
//	}
	
	@Override
	public Page<Usuario> listarUsuariosPorRolUser(Pageable pageable) {
		// TODO Auto-generated method stub
		return usuarioPaginadorRepositorio.listarUsuariosPorRolUser(pageable);
	}

	@Override
	public Usuario findByEmail(String email) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.findByEmail(email);
	}
	
	@Override
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.findByUsername(username);
	}
	
	@Override
	public Optional<Usuario> findById(Long idUsuario) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.findById(idUsuario);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Correo o password inválidos");
		}
		
		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}

	@Override
	public Usuario obtenerDetallesUsuarioPorId(Long id) {
		return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ID: " + id));
	}
	
	@Override
	public void aceptarPermisos(Long usuarioId) {
	    Usuario usuario = usuarioRepositorio.findById(usuarioId)
	            .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + usuarioId));

	    // Aquí puedes realizar acciones adicionales antes de aceptar los permisos, si es necesario
	    // ...

	    // Acepta los permisos (actualiza el estado)
	    Set<Permiso> permisos = usuario.getPermisos();
	    for (Permiso permiso : permisos) {
	        permiso.setEstado(EstadoPermiso.ACEPTADO);
	    }

	    // Guarda el usuario para aplicar los cambios
	    usuarioRepositorio.save(usuario);
	}

	@Override
	public boolean denegarPermisos(Long usuarioId) {
	    Usuario usuario = usuarioRepositorio.findById(usuarioId)
	            .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + usuarioId));

	    // Elimina los permisos en estado PENDIENTE
	    Set<Permiso> permisos = usuario.getPermisos();
	    Iterator<Permiso> iterator = permisos.iterator();
	    while (iterator.hasNext()) {
	        Permiso permiso = iterator.next();
	        if (permiso.getEstado() == EstadoPermiso.PENDIENTE) {
	            iterator.remove();
	            permisoRepositorio.delete(permiso);
	        }
	    }

	    // Guarda el usuario para aplicar los cambios
	    usuarioRepositorio.save(usuario);

	    return true;
	}

}
