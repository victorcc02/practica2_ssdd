package ssdd.practicaWeb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssdd.practicaWeb.entities.Routine;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.repositories.RoutineRepository;
import ssdd.practicaWeb.service.RoutineService;
import ssdd.practicaWeb.service.UserService;

@Controller
public class RoutineController {
    @Autowired
    private RoutineService routineService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoutineRepository routineRepository;


    @GetMapping("/routines")
    public String showAllRoutines(Model model, @RequestParam("id") Long id){
        model.addAttribute("routines",routineService.getAllRoutines(id));
        GymUser user = userService.getGymUser(id);
        if(user != null){
            model.addAttribute("userId",user.getId());
            return "routines";
        }
        return "redirect:/Portada";
    }
    @GetMapping("/routines/{routineId}")
    public String showRoutine(Model model, @PathVariable Long routineId, @RequestParam("id") Long id){
        Routine routine = routineService.getRoutine(routineId);
        GymUser user = userService.getGymUser(id);
        if(routine == null){
            return "redirect:/frontPage?id=" + user.getId();
        }
        if(user == null){
            return "redirect:/frontPage?id=" + user.getId();
        }
        model.addAttribute("routine",routine);
        model.addAttribute("userId",user.getId());
        return "showRoutine";
    }
    @GetMapping("/routines/createRoutine")
    public String createRoutine(Model model, @RequestParam("id") Long id){
        model.addAttribute("routine",new Routine());
        GymUser user = userService.getGymUser(id);
        if(user != null){
            model.addAttribute("userId",user.getId());
            return "createRoutine";
        }
        return "redirect:/frontPage?id=" + user.getId();
    }
    @PostMapping("/routines/createRoutine")
    public String createRoutinePost(Routine routine, @RequestParam("userId") Long id){
        GymUser user = userService.getGymUser(id);
        if (user != null){
            routine.setGymUser(user);
            routineService.createRoutine(routine,user);
        }
        return "redirect:/routines?id=" + user.getId();
    }

    @GetMapping("/routines/editRoutine/{routineId}")
    public String editRoutine(Model model, @PathVariable Long routineId, @RequestParam("id") Long id){
        Routine routine = routineService.getRoutine(routineId);
        GymUser user = userService.getGymUser(id);
        if(user == null){
            return "redirect:/Login";
        }
        if(routine == null){
            return "redirect:/frontPage?id=" + user.getId();
        }
        model.addAttribute("routine",routine);
        model.addAttribute("userId",id);
        return "editRoutine";
    }
    @PostMapping("/routines/editRoutine/{routineId}")
    public String editRoutinePost(Routine routine, @PathVariable Long routineId, @RequestParam("id") Long id){
        GymUser user = userService.getGymUser(id);
        routineService.updateRoutine(routineId,routine, user);
        return "redirect:/routines?id=" + id;
    }
    @GetMapping("/routines/delete/{routineId}")
    public  String deleteRoutinePost(@PathVariable Long routineId, @RequestParam("id") Long id){
        routineService.deleteRoutine(routineId);
        GymUser user = userService.getGymUser(id);
        if(user == null){
            return "redirect:/Login";
        }
        return "redirect:/routines?id=" + user.getId();
    }
}

