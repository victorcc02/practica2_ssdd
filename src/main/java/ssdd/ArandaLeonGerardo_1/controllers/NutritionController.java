package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ssdd.ArandaLeonGerardo_1.entities.Nutrition;
import ssdd.ArandaLeonGerardo_1.service.NutritionService;

@Controller
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

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
    /*
    @GetMapping("/ListaNutricion/ModificarNutricion/{id}")
    public String mostrarVentanaEdliminar(@PathVariable Long id) {
        return "eliminarNutricion";
    }
    @PostMapping("/ListaNutricion/EliminarNutricion/{id}")
    public String eliminadoDeNutricion(@PathVariable Long id) {
        nutricionService.eliminarNutricion(id);
        return "redirect:/ListaNutricion";
    }*/
}


