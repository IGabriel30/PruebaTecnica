package org.prueba.servicios.implementaciones;

import java.util.List;
import java.util.Optional;

import org.prueba.modelos.Producto;
import org.prueba.repositorios.IProductoRepository;
import org.prueba.servicios.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {


    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public Page<Producto> buscarTodosPaginados(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscarPorId(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto crearOEditar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarPorId(Integer id) {
        productoRepository.deleteById(id);
    }

}
