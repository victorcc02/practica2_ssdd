package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssdd.ArandaLeonGerardo_1.entities.Routine;
import ssdd.ArandaLeonGerardo_1.entities.User;
import ssdd.ArandaLeonGerardo_1.service.RoutineService;
import ssdd.ArandaLeonGerardo_1.service.UserService;

@Controller
public class RoutineController {
    @Autowired
    private RoutineService routineService;
    @Autowired
    private UserService userService;
    @GetMapping("/routines")
    public String showAllRoutines(Model model, @RequestParam("id") Long id){
        model.addAttribute("routines",routineService.getAllRoutines());
        User user = userService.getUser(id);
        if(user != null){
            model.addAttribute("userId",user.getId());
            return "routines";
        }
        return "redirect:/Portada";
    }
    @GetMapping("/routines/{id}")
    public String showRoutine(Model model, @PathVariable Long id){
        Routine routine = routineService.getRoutine(id);
        if(routine != null){
            model.addAttribute("routine",routine);
            return "showRoutine";
        }
        return "redirect:/Portada";
    }
    @GetMapping("/routines/createRoutine")
    public String createRoutine(Model model, @RequestParam("id") Long id){
        model.addAttribute("routine",new Routine());
        User user = userService.getUser(id);
        if(user != null){
            model.addAttribute("userId",user.getId());
            return "createRoutine";
        }
        return "redirect:/Portada";
    }
    @PostMapping("/routines/createRoutine")
    public String createRoutinePost(Routine routine, @RequestParam("userId") Long id){
        routineService.createRoutine(routine);
        User user = userService.getUser(id);
        return "redirect:/routines?id=" + user.getId();
    }
    @GetMapping("/routines/editRoutine/{id}")
    public String editRoutine(Model model, @PathVariable Long id){
        Routine routine = routineService.getRoutine(id);
        if(routine != null){
            model.addAttribute("routine",routine);
            return "editRoutine";
        }
        return "redirect:/Portada";
    }
    @PostMapping("/routines/editRoutine/{id}")
    public String editRoutinePost(Routine routine, @PathVariable Long id){
        routineService.updateRoutine(id,routine);
        return "redirect:/routines";
    }
    @PostMapping("/routines/delete/{id}")
    public  String deleteRoutine(@PathVariable Long id){
        routineService.deleteRoutine(id);
        return "redirect:/routines";
    }
}
