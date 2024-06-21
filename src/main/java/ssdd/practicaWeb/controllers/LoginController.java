package ssdd.practicaWeb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.repositories.UserRepository;
import ssdd.practicaWeb.service.UserService;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/Login")
    public String InterfaceLogin(Model model){
        model.addAttribute("user",new GymUser());
        model.addAttribute("error", "");
        return "login";
    }
    @PostMapping("/Login")
    public String goFrontPage(GymUser user, Model model) {
        GymUser optionalGymUser = userService.getGymUser(user.getUsername());
        if(optionalGymUser == null){
            userService.createGymUser(user);
            return "redirect:/FrontPage?userId=" + user.getId();
        }
        if(!optionalGymUser.getPassword().equals(user.getPassword())){
            model.addAttribute("error", "Credenciales invalidos.");
            return "login";
        }
        return "redirect:/FrontPage?userId=" + optionalGymUser.getId();
    }
  
    @GetMapping("/FrontPage")
    public String InterfaceFrontPage(Model model, @RequestParam("userId") Long userId) {
        model.addAttribute("user",userService.getGymUser(userId));
        return "frontPage";
    }

}
