package org.prueba.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String index(){
        return "home/index";
    }
    //public RedirectView redirectToMarcas() {
      //  return new RedirectView("/ordenes");
    //}

    @GetMapping("/login" )
    public String mostrarLogin() {
        return "home/formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/";
    }
}
