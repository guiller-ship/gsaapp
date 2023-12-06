package es.pfc.gsaapp.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import es.pfc.gsaapp.modelo.Permiso;
import es.pfc.gsaapp.modelo.Usuario;
import es.pfc.gsaapp.servicio.PermisoServicioImpl;
import es.pfc.gsaapp.servicio.UsuarioServicio;

@Controller
public class PermisosControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
	
    private final PermisoServicioImpl permisoServicioImpl;

    // Inyecta el servicio en el constructor
    public PermisosControlador(PermisoServicioImpl permisoServicioImpl) {
        this.permisoServicioImpl = permisoServicioImpl;
    }

    @GetMapping("/calendario-permisos")
    public String mostrarCalendario(Model model) {
        model.addAttribute("permiso", new Permiso());
        return "calendario-permisos";
    }

    @PostMapping("/guardar-permiso")
    public String guardarPermiso(@ModelAttribute @Valid Permiso permiso, BindingResult result, String email) {
        if (result.hasErrors()) {
            // Manejar errores de validación
            return "calendario-permisos";
        }
        
     // Obtener el ID del usuario actual (puedes usar el UserDetails de Spring Security)
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());

        // Asignar el usuario al permiso
        permiso.setUsuario(usuario);

        // Lógica para guardar el permiso en la base de datos
        permisoServicioImpl.guardarPermiso(permiso);

//        // Después de autenticar al usuario
//        Usuario usuario = usuarioRepositorio.findByEmail(email);
//        // Otros procesos de tu aplicación
//        // ...
//        // Crear y guardar un permiso para el usuario
//        permisoServicio.crearPermisoParaUsuario(usuario);


        // Redirige a la página de éxito o a donde sea necesario
        return "redirect:/inicio"; // Ajusta según tu aplicación
    }
}
