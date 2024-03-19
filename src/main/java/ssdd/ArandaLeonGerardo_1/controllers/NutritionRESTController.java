package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssdd.ArandaLeonGerardo_1.entities.Nutrition;
import ssdd.ArandaLeonGerardo_1.service.NutritionService;

import java.util.Collection;

@Controller
@RequestMapping("/api/nutrition")
public class NutritionRESTController {

    @Autowired
    private NutritionService nutritionService;
    @PostMapping
    public ResponseEntity<Nutrition> crearNutricion(@RequestBody Nutrition nutrition) {
        return ResponseEntity.status(201).body(nutritionService.crearNutricion(nutrition));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nutrition> obtenerNutricion(@PathVariable Long id) {
        Nutrition nutrition = nutritionService.obtenerNutricion(id);
        if (nutrition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nutrition);
    }

    @GetMapping
    public ResponseEntity<Collection<Nutrition>> obtenerTodasLasNutricion() {
        return ResponseEntity.ok(nutritionService.obtenerTodasLasNutricion());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nutrition> actualizarNutricion(@PathVariable Long id, @RequestBody Nutrition nutrition) {
        Nutrition actualizada = nutritionService.actualizarNutricion(id, nutrition);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNutricion(@PathVariable Long id) {
        nutritionService.eliminarNutricion(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Nutrition> actualizarParcialmenteNutricion(@PathVariable Long id, @RequestBody Nutrition parcialNutrition) {
        Nutrition existente = nutritionService.obtenerNutricion(id);
        if (existente == null){
            return ResponseEntity.notFound().build();
        }
        if (parcialNutrition.getNombre() != null) {
            existente.setNombre(parcialNutrition.getNombre());
        }
        if (parcialNutrition.getTipo() != null) {
            existente.setTipo(parcialNutrition.getTipo());
        }
        if (parcialNutrition.getComida() != null) {
            existente.setComida(parcialNutrition.getComida());
        }
        nutritionService.actualizarNutricion(id,existente);
        return ResponseEntity.ok(existente);
    }
}
