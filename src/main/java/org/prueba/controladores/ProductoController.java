package org.prueba.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.prueba.modelos.Producto;
import org.prueba.servicios.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("productos")
public class ProductoController {
    
    @Autowired
    private IProductoService productoService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Producto> productos = productoService.buscarTodosPaginados(pageable);
        model.addAttribute("productos", productos);

        int totalPages = productos.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "producto/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("producto") Producto producto, BindingResult result, Model model,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("producto", producto);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "producto/create";
        }

        productoService.crearOEditar(producto);
        attributes.addFlashAttribute("msg", "producto creado correctamente");
        return "redirect:/productos";
    }


     @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.buscarPorId(id).get();
        model.addAttribute("producto", producto);
        return "producto/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("producto") Producto producto, BindingResult result, Model model,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("producto", producto);
            attributes.addFlashAttribute("error", "No se pudo modificar debido a un error.");
            return "producto/edit";
        }

        productoService.crearOEditar(producto);
        attributes.addFlashAttribute("msg", "producto modificado correctamente");
        return "redirect:/productos";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.buscarPorId(id).get();
        model.addAttribute("producto", producto);
        return "producto/delete";
    }

    @PostMapping("/delete")
    public String delete(Producto producto, RedirectAttributes attributes) {
        productoService.eliminarPorId(producto.getId());
        attributes.addFlashAttribute("msg", "producto eliminado correctamente");
        return "redirect:/productos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.buscarPorId(id).get();
        model.addAttribute("producto", producto);
        return "producto/details";
    }

}
