package es.pfc.gsaapp.controlador;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginControlador {
	
	@GetMapping("/login")
	public String verPaginaInicioSesion(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required = false) String logout,
			Model modelo, Principal principal, RedirectAttributes flash,
			HttpServletRequest request, HttpServletResponse response) {
		
		// Verificar si ya hay una sesión activa
		if(principal != null) {
			flash.addFlashAttribute("info", "Ya ha iniciado sesión anteriormente");
			return "redirect:/";
		}
		
		// Limpiar la sesión al cargar la página de inicio de sesión
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		
		if(error != null) {
			modelo.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}
		
		if(logout != null) {
			modelo.addAttribute("success", "Ha cerrado sesión con éxito!");
		}
		
		modelo.addAttribute("titulo", "Inicio sesión");
		
		return "login";
	}
}