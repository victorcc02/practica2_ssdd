package ssdd.practicaWeb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.service.UserService;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;


    @GetMapping("/Login")
    public String InterfaceLogin(Model model){
        model.addAttribute("user",new GymUser());
        return "login";
    }
    @PostMapping("/Login")
    public String goFrontPage(GymUser user) {
        userService.createGymUser(user);
        return "redirect:/FrontPage?id=" + user.getId();
    }
    @GetMapping("/FrontPage")
    public String InterfaceFrontPage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user",userService.getGymUser(id));
        return "frontPage";
    }
}
