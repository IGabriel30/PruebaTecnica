package org.prueba.repositorios;

import org.prueba.modelos.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenRepository  extends JpaRepository<Orden, Long>{

}
