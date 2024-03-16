package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ssdd.ArandaLeonGerardo_1.entities.Nutricion;
import ssdd.ArandaLeonGerardo_1.service.NutricionService;

@Controller
public class nutricionControlador {

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
        model.addAttribute("nutricion",new Nutricion());
        return "crearNutricion";
    }
    @PostMapping("/ListaNutricion/CrearNutricion")
    public String agregarNutricion(Nutricion alimento){
        nutricionService.crearNutricion(alimento);
        return "redirect:/ListaNutricion";
    }
    @GetMapping("/ListaNutricion/detalleNutricion/{id}")
    public String detalladoDeNutricion(@PathVariable Long id, Model model){
        Nutricion nutricion = nutricionService.obtenerNutricion(id);
        if (nutricion == null) {
            return "redirect:/ListaNutricion";
        }
        model.addAttribute("nutricion", nutricion);
        return "detalleNutricion";
    }

    @GetMapping("/ListaNutricion/ModificarNutricion/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Nutricion nutricion = nutricionService.obtenerNutricion(id);
        if (nutricion == null) {
            return "redirect:/ListaNutricion";
        }
        model.addAttribute("nutricion", nutricion);
        return "ModificarNutricion";
    }
    @PostMapping("/ListaNutricion/ModificarNutricion")
    public String editadoDeNutricion(Nutricion nutricion) {
        nutricionService.actualizarNutricion(nutricion.getId(),nutricion);
        return "redirect:/ListaNutricion";
    }
    @GetMapping("/ListaNutricion/EliminarNutricion/{id}")
    public String eliminadoDeNutricion(@PathVariable Long id) {
        nutricionService.eliminarNutricion(id);
        return "redirect:/ListaNutricion";
    }
}


