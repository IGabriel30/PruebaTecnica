package org.prueba.controladores;

import java.util.stream.Collectors;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.prueba.servicios.interfaces.IDetalleOrdenService;
import org.prueba.servicios.interfaces.IOrdenService;
import org.prueba.modelos.DetalleOrden;
import org.prueba.modelos.Orden;
import org.prueba.modelos.Producto;
import org.springframework.data.domain.Page;
import org.prueba.servicios.interfaces.IProductoService;
import org.springframework.ui.Model;
import java.util.stream.IntStream;
import java.util.Optional;

@Controller
@RequestMapping("/detallesOrden") 
public class DetalleOrdenController {
    @Autowired
    private IDetalleOrdenService detalleOrdenService; 
    @Autowired 
    private IProductoService productoService; 
    @Autowired
    private IOrdenService ordenService; 

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<DetalleOrden> detalleOrdenes = detalleOrdenService.buscarTodosPaginados(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("detalleOrdenes", detalleOrdenes);

        int totalPages = detalleOrdenes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "detalleOrden/index"; 
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("detalleOrden", new DetalleOrden());
    
        List<Producto> productos = productoService.obtenerTodos();
        List<Orden> ordenes = ordenService.obtenerTodos();
    
        model.addAttribute("productos", productos);
        model.addAttribute("ordenes", ordenes);
    
        return "detalleOrden/create"; 
    }
     @PostMapping("/save")
    public String save(@ModelAttribute("detalleOrden") DetalleOrden detalleOrden, RedirectAttributes redirectAttributes) {
        detalleOrdenService.crearOEditar(detalleOrden);
        redirectAttributes.addFlashAttribute("msg", "Detalle de orden guardado exitosamente!");
        return "redirect:/detallesOrden"; 
    }

     @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        DetalleOrden detalleOrden = detalleOrdenService.buscarPorId(id);
        model.addAttribute("detalleOrden", detalleOrden);

        List<Producto> productos = productoService.obtenerTodos();
        List<Orden> ordenes = ordenService.obtenerTodos();

        model.addAttribute("productos", productos);
        model.addAttribute("ordenes", ordenes);

        return "detalleOrden/edit"; 
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("detalleOrden") DetalleOrden detalleOrden, RedirectAttributes redirectAttributes) {
        detalleOrdenService.crearOEditar(detalleOrden);
        redirectAttributes.addFlashAttribute("msg", "Detalle de orden actualizado exitosamente!");
        return "redirect:/detallesOrden"; 
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id, Model model) {
        DetalleOrden detalleOrden = detalleOrdenService.buscarPorId(id);
        model.addAttribute("detalleOrden", detalleOrden);
        return "detalleOrden/delete"; 
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        detalleOrdenService.eliminarPorId(id);
        redirectAttributes.addFlashAttribute("msg", "Detalle de orden eliminado exitosamente!");
        return "redirect:/detallesOrden"; 
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        DetalleOrden detalleOrden = detalleOrdenService.buscarPorId(id);
        model.addAttribute("detalleOrden", detalleOrden);
        return "detalleOrden/details"; 
    }
}
