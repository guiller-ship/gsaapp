package es.pfc.gsaapp.controlador;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.pfc.gsaapp.modelo.Permiso;
import es.pfc.gsaapp.modelo.Usuario;
import es.pfc.gsaapp.modelo.tipos.EstadoPermiso;
import es.pfc.gsaapp.servicio.UsuarioServicio;
import es.pfc.gsaapp.util.paginator.PageRender;

@Controller
public class InicioControlador {
	
    @Autowired
    private UsuarioServicio usuarioServicio;  // Inyección de dependencia del servicio de usuario

    @GetMapping(value = {"/", "/inicio"})
    public String verPaginaDeInicio(Model modelo, Authentication autenticacion,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size) {
        String email = autenticacion.getName();
        Usuario usuario = usuarioServicio.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        modelo.addAttribute("titulo", "Inicio");
        modelo.addAttribute("usuario", usuario);

        Set<Permiso> permisos = usuario.getPermisos();
        modelo.addAttribute("permisos", permisos);

        // Paginador
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> usuariosPage = usuarioServicio.listarUsuariosPorRolUser(pageable);
        
        PageRender<Usuario> pageRender = new PageRender<>("/inicio", usuariosPage);

        modelo.addAttribute("usuariosUser", usuariosPage);
        modelo.addAttribute("page", pageRender);

        return "inicio";
    }

    @GetMapping("/inicio/verAsunto/{idUsuario}")
    public String verDetalleUsuario(@PathVariable Long idUsuario, Model modelo) {
        Usuario usuario = usuarioServicio.obtenerDetallesUsuarioPorId(idUsuario);
        Set<Permiso> permisos = usuario.getPermisos();
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("permisos", permisos);
        return "verAsunto"; // Nombre de la nueva vista
    }

    @PostMapping("/inicio/denegarPermisos")
    public String denegarPermisos(@RequestParam Long usuarioId, Model modelo) {
        Optional<Usuario> optionalUsuario = usuarioServicio.findById(usuarioId);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            // Verifica si los permisos ya fueron aceptados
            if (usuario.getPermisos().stream().anyMatch(permiso -> permiso.getEstado() == EstadoPermiso.ACEPTADO)) {
                // Muestra un alert en la vista indicando que no se pueden denegar permisos ya aceptados
                modelo.addAttribute("alertMessage", "No se pueden denegar permisos ya aceptados.");
            } else {
                // Realiza la acción correspondiente (denegar)
                usuarioServicio.denegarPermisos(usuarioId);
            }
        }

        return "redirect:/inicio";
    }

    @PostMapping("/inicio/aceptarPermisos")
    public String aceptarPermisos(@RequestParam Long usuarioId, @RequestParam String accion, Model modelo) {
        Optional<Usuario> optionalUsuario = usuarioServicio.findById(usuarioId);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            // Realizar la acción correspondiente (aceptar o denegar)
            if ("ACEPTAR".equals(accion)) {
                usuarioServicio.aceptarPermisos(usuarioId);
            }
        }

        return "redirect:/inicio";
    }
}