package org.prueba.servicios.interfaces;

import java.util.List;
import java.util.Optional;

import org.prueba.modelos.Producto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface IProductoService {
    Page<Producto> buscarTodosPaginados(Pageable pageable);

    List<Producto> obtenerTodos();

    Optional<Producto> buscarPorId(Integer id);

    Producto crearOEditar(Producto producto);

    void eliminarPorId(Integer id);
}
