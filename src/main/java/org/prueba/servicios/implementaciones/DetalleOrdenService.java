package org.prueba.servicios.implementaciones;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.prueba.repositorios.IDetalleOrdenRepository;
import org.prueba.modelos.DetalleOrden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.prueba.servicios.interfaces.IDetalleOrdenService;

@Service
public class DetalleOrdenService implements IDetalleOrdenService{
    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public Page<DetalleOrden> buscarTodosPaginados(Pageable pageable) {
        return detalleOrdenRepository.findAll(pageable);
    }

    @Override
    public List<DetalleOrden> obtenerTodos() {
        return detalleOrdenRepository.findAll();
    }

    @Override
    public DetalleOrden buscarPorId(Long id) {
        Optional<DetalleOrden> detalleOrdenOptional = detalleOrdenRepository.findById(id);
        if (detalleOrdenOptional.isPresent()) {
            return detalleOrdenOptional.get();
        } else {
            throw new RuntimeException("Detalle de Orden no encontrado con ID: " + id);
        }
    }

    @Override
    public DetalleOrden crearOEditar(DetalleOrden detalleOrdenIJGZ) {
        return detalleOrdenRepository.save(detalleOrdenIJGZ);
    }

    @Override
    public void eliminarPorId(Long id) {
        if (detalleOrdenRepository.existsById(id)) {
            detalleOrdenRepository.deleteById(id);
        } else {
            throw new RuntimeException("Detalle de Orden no encontrado con ID: " + id);
        }
    }
}
