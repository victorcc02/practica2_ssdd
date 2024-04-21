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


import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/Login")
    public String InterfaceLogin(Model model){
        model.addAttribute("user",new GymUser());
        return "login";
    }
    @PostMapping("/Login")
    public String goFrontPage(GymUser user) {
        //Optional<GymUser> theGymUser = userRepository.findById(user.getId());
        /*if (theGymUser.isPresent()){
            return "redirect:/FrontPage?id=" + user.getId();
        }else {
            userService.createGymUser(user);
            return "redirect:/FrontPage?id=" + user.getId();
        }*/

        GymUser optionalGymUser = userService.getGymUser(user.getUsername());
        if(optionalGymUser == null){
            userService.createGymUser(user);
            return "redirect:/FrontPage?id=" + user.getId();
        }
        return "redirect:/FrontPage?id=" + optionalGymUser.getId();
    }
  
    @GetMapping("/FrontPage")
    public String InterfaceFrontPage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user",userService.getGymUser(id));
        return "frontPage";
    }

}
