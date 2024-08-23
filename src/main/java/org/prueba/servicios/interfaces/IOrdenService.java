package org.prueba.servicios.interfaces;

import java.util.List;
import java.util.Optional;

import org.prueba.modelos.Orden;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface IOrdenService {
    Page<Orden> buscarTodosPaginados(Pageable pageable);

    List<Orden> obtenerTodos();

    Optional<Orden> buscarPorId(Long id);

    Orden crearOEditar(Orden marca);

    void eliminarPorId(Long id);
}
