package org.prueba.servicios.interfaces;

import java.util.List;

import org.apache.el.stream.Optional;
import org.prueba.modelos.Producto;
import org.prueba.modelos.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IUsuarioService {
    
    Page<Usuario> obtenerTodosPaginados(Pageable pageable);

    List<Usuario> obtenerTodos();

    

    Usuario obtenerPorId(Integer id);

    Usuario crearOEditar(Usuario usuario);

    void eliminarPorId(Integer id);
}
