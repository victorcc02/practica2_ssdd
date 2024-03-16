package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutineController {
    @GetMapping("/Routines")
    public String showRoutines(){
        return "";
    }
}
