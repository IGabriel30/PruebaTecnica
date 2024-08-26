package org.prueba.servicios.implementaciones;

import java.util.List;

import org.prueba.modelos.Rol;
import org.prueba.modelos.Usuario;
import org.prueba.repositorios.IRolRepository;
import org.prueba.servicios.interfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService implements IRolService{
     @Autowired
    private IRolRepository rolRepository;
    @Override
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }


}
