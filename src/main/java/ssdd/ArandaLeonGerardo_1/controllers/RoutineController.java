package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
