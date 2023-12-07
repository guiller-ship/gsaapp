package es.pfc.gsaapp.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarioControlador {
	
    @GetMapping("/calendario")
    public String verPaginaCalendario(Model modelo) {
    	
    	modelo.addAttribute("titulo", "Calendario");
    	
        return "calendario-permisos";
    }

}
