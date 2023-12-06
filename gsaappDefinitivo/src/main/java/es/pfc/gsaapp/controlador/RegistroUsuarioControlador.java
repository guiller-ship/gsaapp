package es.pfc.gsaapp.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.pfc.gsaapp.controlador.dto.UsuarioDTO;
import es.pfc.gsaapp.servicio.UsuarioServicio;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

	private UsuarioServicio usuarioServicio;

	public RegistroUsuarioControlador(UsuarioServicio usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}
	
	@ModelAttribute("usuario")
	public UsuarioDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarUsuario(@ModelAttribute("usuario") UsuarioDTO registroDTO) {
		usuarioServicio.guardar(registroDTO);
		return "redirect:/registro?exito";
	}
}
