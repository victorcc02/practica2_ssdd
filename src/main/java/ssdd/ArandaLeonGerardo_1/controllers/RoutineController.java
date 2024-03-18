package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ssdd.ArandaLeonGerardo_1.entities.Routine;
import ssdd.ArandaLeonGerardo_1.service.RoutineService;

@Controller
public class RoutineController {
    @Autowired
    private RoutineService routineService;
    @GetMapping("/routines")
    public String showAllRoutines(Model model){
        model.addAttribute("routines",routineService.getAllRoutines());
        return "routines";
    }
    @GetMapping("/routines/{id}")
    public String showRoutine(Model model, @PathVariable Long id){
        Routine routine = routineService.getRoutine(id);
        if(routine != null){
            model.addAttribute("routine",routine);
            return "routine";
        }
        return "redirect:/Portada";
    }
    @GetMapping("/routines/createRoutine")
    public String createRoutine(Model model){
        model.addAttribute("routine",new Routine());
        return "createRoutine";
    }
    @PostMapping("/routines/createRoutine")
    public String createRoutinePost(Routine routine){
        routineService.createRoutine(routine);
        return "redirect:/routines";
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
