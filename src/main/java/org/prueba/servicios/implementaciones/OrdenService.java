package org.prueba.servicios.implementaciones;

import java.util.List;
import java.util.Optional;

import org.prueba.modelos.Orden;
import org.prueba.repositorios.IOrdenRepository;
import org.prueba.servicios.interfaces.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrdenService implements IOrdenService {

     @Autowired
    private IOrdenRepository ordenRepository;
    @Override
    public Page<Orden> buscarTodosPaginados(Pageable pageable) {
        return ordenRepository.findAll(pageable);
    }

    @Override
    public List<Orden> obtenerTodos() {
        return ordenRepository.findAll();
    }

    @Override
    public Optional<Orden> buscarPorId(Long id) {
        return ordenRepository.findById(id);
    }

    @Override
    public Orden crearOEditar(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public void eliminarPorId(Long id) {
        ordenRepository.deleteById(id);
    }

}
