package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ssdd.ArandaLeonGerardo_1.entities.Nutrition;
import ssdd.ArandaLeonGerardo_1.service.NutricionService;

@Controller
public class NutritionController {

    @Autowired
    private NutricionService nutricionService;

    @GetMapping("/Nutricion")
    public String InterfazNutricion() {
        return "Nutricion";
    }
    @GetMapping("/ListaNutricion")
    public String InterfazListaNutricion(Model model) {
        model.addAttribute("nutricion",nutricionService.obtenerTodasLasNutricion());
        return "listaNutricion";
    }
    @GetMapping("/ListaNutricion/CrearNutricion")
    public String InterfazCrearNutricion(Model model) {
        model.addAttribute("nutricion",new Nutrition());
        return "crearNutricion";
    }
    @PostMapping("/ListaNutricion/CrearNutricion")
    public String agregarNutricion(Nutrition alimento){
        nutricionService.crearNutricion(alimento);
        return "redirect:/ListaNutricion";
    }
    @GetMapping("/ListaNutricion/detalleNutricion/{id}")
    public String detalladoDeNutricion(@PathVariable Long id, Model model){
        Nutrition nutrition = nutricionService.obtenerNutricion(id);
        if (nutrition == null) {
            return "redirect:/ListaNutricion";
        }
        model.addAttribute("nutricion", nutrition);
        return "detalleNutricion";
    }

    @GetMapping("/ListaNutricion/ModificarNutricion/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Nutrition nutrition = nutricionService.obtenerNutricion(id);
        if (nutrition == null) {
            return "redirect:/ListaNutricion";
        }
        model.addAttribute("nutricion", nutrition);
        return "ModificarNutricion";
    }
    @PostMapping("/ListaNutricion/ModificarNutricion")
    public String editadoDeNutricion(Nutrition nutrition) {
        nutricionService.actualizarNutricion(nutrition.getId(), nutrition);
        return "redirect:/ListaNutricion";
    }
    @GetMapping("/ListaNutricion/EliminarNutricion/{id}")
    public String eliminadoDeNutricion(@PathVariable Long id) {
        nutricionService.eliminarNutricion(id);
        return "redirect:/ListaNutricion";
    }
}


