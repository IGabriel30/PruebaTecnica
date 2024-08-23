package org.prueba.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.prueba.modelos.Orden;
import org.prueba.servicios.interfaces.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.stream.IntStream;

@Controller
@RequestMapping("ordenes")
public class OrdenController {
     @Autowired
    private IOrdenService ordenService;
  
    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Orden> ordenes = ordenService.buscarTodosPaginados(pageable);
        model.addAttribute("ordenes", ordenes);

        int totalPages = ordenes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "orden/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("orden", new Orden());
        return "orden/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("orden") Orden orden, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("orden", orden);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "orden/create";
        }

        ordenService.crearOEditar(orden);
        attributes.addFlashAttribute("msg", "Orden creada correctamente");
        return "redirect:/ordenes";
    }
}
