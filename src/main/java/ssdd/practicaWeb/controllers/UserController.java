package ssdd.practicaWeb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.repositories.UserRepository;
import ssdd.practicaWeb.service.UserService;

@Controller
public class UserController {
    /*Due to security reasons, it will not be possible to view the information of all
     * existing users. A user will only be able to see details corresponding to
     * their own account*/
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/{id}")
    public String showProfile(Model model, @PathVariable Long id){
        GymUser user = userService.getGymUser((id));
        if(user != null){
            model.addAttribute("user",user);
            return "users";
        }
        return "redirect:/FrontPage";
    }

    @GetMapping("/users/edit/{id}")
    public String editeProfileGet(Model model,@PathVariable Long id){
        GymUser user = userService.getGymUser((id));
        if(user != null){
            model.addAttribute("user",user);
            return "edit";
        }
        return "redirect:/FrontPage";
    }

    @PostMapping("/users/edit/{id}")
    public String editProfilePost(GymUser user, @PathVariable Long id){
        userService.updateGymUser(id, user);
        return "redirect:/users/{id}";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteGymUser(id);
        return "redirect:/Login";
    }
}
