package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssdd.ArandaLeonGerardo_1.entities.Nutrition;
import ssdd.ArandaLeonGerardo_1.service.NutricionService;

import java.util.Collection;

@Controller
@RequestMapping("/api/nutricion")
public class NutritionRESTController {

    @Autowired
    private NutricionService nutricionService;
    @PostMapping
    public ResponseEntity<Nutrition> crearNutricion(@RequestBody Nutrition nutrition) {
        return ResponseEntity.status(201).body(nutricionService.crearNutricion(nutrition));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nutrition> obtenerNutricion(@PathVariable Long id) {
        Nutrition nutrition = nutricionService.obtenerNutricion(id);
        if (nutrition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nutrition);
    }

    @GetMapping
    public ResponseEntity<Collection<Nutrition>> obtenerTodasLasNutricion() {
        return ResponseEntity.ok(nutricionService.obtenerTodasLasNutricion());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nutrition> actualizarNutricion(@PathVariable Long id, @RequestBody Nutrition nutrition) {
        Nutrition actualizada = nutricionService.actualizarNutricion(id, nutrition);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNutricion(@PathVariable Long id) {
        nutricionService.eliminarNutricion(id);
        return ResponseEntity.ok().build();
    }
}
