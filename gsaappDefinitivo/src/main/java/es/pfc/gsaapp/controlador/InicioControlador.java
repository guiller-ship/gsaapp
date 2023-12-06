package es.pfc.gsaapp.controlador;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

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
import es.pfc.gsaapp.servicio.UsuarioServicio;
import es.pfc.gsaapp.util.paginator.PageRender;

@Controller
public class InicioControlador {
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	// Utilizamos una variable atómica para representar el estado de permisosDenegados
    private final AtomicBoolean permisosDenegados = new AtomicBoolean(false);

    @Autowired
    private UsuarioServicio usuarioServicio;  // Inyección de dependencia del servicio de usuario

    @GetMapping(value = {"/", "/inicio"})
    public String verPaginaDeInicio(Model modelo, Authentication autenticacion,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "7") int size) {
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
    public String denegarPermisos(@RequestParam Long usuarioId, @RequestParam String accion, Model modelo) {
        Optional<Usuario> optionalUsuario = usuarioServicio.findById(usuarioId);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            // Realizar la acción correspondiente (aceptar o denegar)
            if ("DENEGAR".equals(accion)) {
                usuario.setPermisosDenegados(true);
                usuarioServicio.denegarPermisos(usuarioId);

                // Restablecer permisosDenegados después de cierto tiempo (ejemplo: 5 segundos)
                scheduler.schedule(() -> {
                    usuario.setPermisosDenegados(false);
                    permisosDenegados.set(false); // Actualiza la variable atómica

                    // Actualiza el modelo dentro del hilo de la tarea programada
                    modelo.addAttribute("permisosDenegados", false);
                }, 5, TimeUnit.SECONDS);
            }
        }

        return "redirect:/inicio";
    }
}