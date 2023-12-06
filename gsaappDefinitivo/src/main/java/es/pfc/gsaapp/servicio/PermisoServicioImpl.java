package es.pfc.gsaapp.servicio;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.pfc.gsaapp.modelo.Permiso;
import es.pfc.gsaapp.repositorio.PermisoRepositorio;

@Service
public class PermisoServicioImpl implements PermisoServicio {

    @Autowired
    private PermisoRepositorio permisoRepositorio;

    public void guardarPermiso(Permiso permiso) {
        permisoRepositorio.save(permiso);
    }
	
	@Override
	public Collection<String> mapearUsuariosPermisos(Collection<Permiso> permisos) {
	    return permisos.stream().map(Permiso::getTipoPermiso).collect(Collectors.toList());
	}
}