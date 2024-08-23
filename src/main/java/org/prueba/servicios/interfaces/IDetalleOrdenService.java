package org.prueba.servicios.interfaces;

import java.util.List;

import org.prueba.modelos.DetalleOrden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDetalleOrdenService {
    Page<DetalleOrden> buscarTodosPaginados(Pageable pageable);

    List<DetalleOrden> obtenerTodos();

    DetalleOrden buscarPorId(Long id);

    DetalleOrden crearOEditar(DetalleOrden detalleOrden);

    void eliminarPorId(Long id);
}
