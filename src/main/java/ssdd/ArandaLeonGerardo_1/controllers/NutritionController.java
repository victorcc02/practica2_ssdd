package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ssdd.ArandaLeonGerardo_1.entities.Nutrition;
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
        Nutrition nutricionGimnasio = new Nutrition("Nutricion 2", "batido","proteinas");

        nutritionService.crearNutricion(nutricionNueces);
        nutritionService.crearNutricion(nutricionEnsalada);
        nutritionService.crearNutricion(nutricionGimnasio);
    }
    
    @GetMapping("/Nutricion")
    public String InterfazNutricion() {
        return "nutrition";
    }
    @GetMapping("/ListaNutricion")
    public String InterfazListaNutricion(Model model) {
        model.addAttribute("nutrition", nutritionService.obtenerTodasLasNutricion());
        return "listNutrition";
    }
    @GetMapping("/ListaNutricion/CrearNutricion")
    public String InterfazCrearNutricion(Model model) {
        model.addAttribute("nutricion",new Nutrition());
        return "createNutrition";
    }
    @PostMapping("/ListaNutricion/CrearNutricion")
    public String agregarNutricion(Nutrition food){
        nutritionService.crearNutricion(food);
        return "redirect:/ListaNutricion";
    }
    @GetMapping("/ListaNutricion/detalleNutricion/{id}")
    public String detalladoDeNutricion(@PathVariable Long id, Model model){
        Nutrition nutrition = nutritionService.obtenerNutricion(id);
        if (nutrition == null) {
            return "redirect:/ListaNutricion";
        }
        model.addAttribute("nutrition", nutrition);
        return "detailsNutrition";
    }

    @GetMapping("/ListaNutricion/ModificarNutricion/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Nutrition nutrition = nutritionService.obtenerNutricion(id);
        if (nutrition == null) {
            return "redirect:/ListaNutricion";
        }
        model.addAttribute("nutrition", nutrition);
        return "modifyNutrition";
    }
    @PostMapping("/ListaNutricion/ModificarNutricion")
    public String editadoDeNutricion(Nutrition nutrition) {
        nutritionService.actualizarNutricion(nutrition.getId(), nutrition);
        return "redirect:/ListaNutricion";
    }
    @GetMapping("/ListaNutricion/EliminarNutricion/{id}")
    public String mostrarFormularioEliminar(@PathVariable Long id, Model model) {
        Nutrition nutrition = nutritionService.obtenerNutricion(id);
        model.addAttribute("nutrition", nutrition);
        return "deleteNutrition";
    }
    @GetMapping("/ListaNutricion/EliminarLaNutricion/{id}")
    public String eliminadoDeNutricion(@PathVariable Long id) {
        nutritionService.eliminarNutricion(id);
        return "redirect:/ListaNutricion";
    }
}


