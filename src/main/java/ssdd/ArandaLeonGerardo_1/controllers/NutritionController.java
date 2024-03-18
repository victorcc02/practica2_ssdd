package ssdd.ArandaLeonGerardo_1.controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ssdd.ArandaLeonGerardo_1.entities.Nutrition;
import ssdd.ArandaLeonGerardo_1.entities.User;
import ssdd.ArandaLeonGerardo_1.service.NutritionService;
import ssdd.ArandaLeonGerardo_1.service.UserService;

@Controller
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;
    @Autowired
    private UserService userService;

    @PostConstruct
    public void nutritionController(){
        Nutrition nutricionNueces = new Nutrition("Nutricion 1", "nueces","volumen");
        Nutrition nutricionEnsalada = new Nutrition("Nutricion 2", "ensalada","adelgazar");
        Nutrition nutricionGimnasio = new Nutrition("Nutricion 3", "batido","proteinas");

        nutritionService.crearNutricion(nutricionNueces);
        nutritionService.crearNutricion(nutricionEnsalada);
        nutritionService.crearNutricion(nutricionGimnasio);
    }

    @GetMapping("/Nutrition")
    public String InterfazNutricion() {
        User user = userService.getUser(id);
        if (user != null){
            model.addAttribute("userId",user.getId());
            return "nutrition";
        }
        return "redirect:/FrontPage";
    }
    @GetMapping("/ListNutrition")
    public String InterfazListaNutricion(Model model) {
        model.addAttribute("nutrition", nutritionService.obtenerTodasLasNutricion());
        User user = userService.getUser(id);
        if (user != null){
            model.addAttribute("userId",user.getId());
            return "listNutrition";
        }
        return "redirect:/Nutrition";
    }
    @GetMapping("/ListNutrition/CreateNutrition")
    public String InterfazCrearNutricion(Model model) {
        model.addAttribute("nutricion",new Nutrition());
        return "createNutrition";
    }
    @PostMapping("/ListNutrition/CreateNutrition")
    public String agregarNutricion(Nutrition food){
        nutritionService.crearNutricion(food);
        return "redirect:/ListNutrition";
    }
    @GetMapping("/ListNutrition/detailsNutrition/{id}")
    public String detalladoDeNutricion(@PathVariable Long id, Model model){
        Nutrition nutrition = nutritionService.obtenerNutricion(id);
        if (nutrition == null) {
            return "redirect:/ListNutrition";
        }
        model.addAttribute("nutrition", nutrition);
        return "detailsNutrition";
    }

    @GetMapping("/ListNutrition/ModifyNutrition/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Nutrition nutrition = nutritionService.obtenerNutricion(id);
        if (nutrition == null) {
            return "redirect:/ListNutrition";
        }
        model.addAttribute("nutrition", nutrition);
        return "modifyNutrition";
    }
    @PostMapping("/ListNutrition/ModifyNutrition/{id}")
    public String editadoDeNutricion(Nutrition nutrition) {
        nutritionService.actualizarNutricion(nutrition.getId(), nutrition);
        return "redirect:/ListNutrition";
    }
    @GetMapping("/ListNutrition/DeleteNutrition/{id}")
    public String mostrarFormularioEliminar(@PathVariable Long id, Model model) {
        Nutrition nutrition = nutritionService.obtenerNutricion(id);
        model.addAttribute("nutrition", nutrition);
        return "deleteNutrition";
    }
    @GetMapping("/ListNutrition/DeleteTheNutrition/{id}")
    public String eliminadoDeNutricion(@PathVariable Long id) {
        nutritionService.eliminarNutricion(id);
        return "redirect:/ListNutrition";
    }
}


