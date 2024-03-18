package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssdd.ArandaLeonGerardo_1.entities.User;
import ssdd.ArandaLeonGerardo_1.service.UserService;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/Inicio")
    public String InterfazInicio(Model model){
        model.addAttribute("user",new User());
        return "login";
    }
    @PostMapping("/Inicio")
    public String irPortada(User user) {
        userService.createUser(user);
        return "redirect:/Portada?id=" + user.getId();
    }
    @GetMapping("/Portada")
    public String InterfazPortada(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user",userService.getUser(id));
        return "frontPage";
    }
}
