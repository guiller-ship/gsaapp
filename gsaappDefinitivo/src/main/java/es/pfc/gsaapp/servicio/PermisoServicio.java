package es.pfc.gsaapp.servicio;

import java.util.Collection;

import es.pfc.gsaapp.modelo.Permiso;

public interface PermisoServicio {
	
    void guardarPermiso(Permiso permiso);

	Collection<String> mapearUsuariosPermisos(Collection<Permiso> permisos);
}