package org.prueba.repositorios;

import org.prueba.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}
