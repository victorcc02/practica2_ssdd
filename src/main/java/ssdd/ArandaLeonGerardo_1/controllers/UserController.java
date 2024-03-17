package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ssdd.ArandaLeonGerardo_1.entities.User;
import ssdd.ArandaLeonGerardo_1.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public String showProfile(Model model, @PathVariable Long id){
        User user = userService.getUser((id));
        if(user != null){
            model.addAttribute("user",user);
            return "users";
        }
        return "redirect:/Portada";
    }

    @GetMapping("/users/edit/{id}")
    public String editeProfileGet(Model model,@PathVariable Long id){
        User user = userService.getUser((id));
        if(user != null){
            model.addAttribute("user",user);
            return "edit";
        }
        return "redirect:/Portada";
    }

    @PostMapping("/users/edit/{id}")
    public String editProfilePost(User user, @PathVariable Long id){
        userService.updateUser(id, user);
        return "redirect:/users/{id}";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/Inicio";
    }
}
