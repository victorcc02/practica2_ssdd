package ssdd.practicaWeb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssdd.practicaWeb.entities.User;
import ssdd.practicaWeb.service.UserService;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/Login")
    public String InterfazInicio(Model model){
        model.addAttribute("user",new User());
        return "login";
    }
    @PostMapping("/Login")
    public String irPortada(User user) {
        userService.createUser(user);
        return "redirect:/FrontPage?id=" + user.getId();
    }
    @GetMapping("/FrontPage")
    public String InterfazPortada(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user",userService.getUser(id));
        return "frontPage";
    }
}
