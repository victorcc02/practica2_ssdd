package ssdd.practicaWeb.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.entities.Nutrition;
import ssdd.practicaWeb.service.NutritionService;

import java.util.Collection;

@Controller
@RequestMapping("/api/nutrition")
public class NutritionRESTController {

    @Autowired
    private NutritionService nutritionService;
    interface DetailedView extends Nutrition.PublicNutrition, Nutrition.AsociationUserNutrition, Nutrition.ListFood{}

    @PostMapping
    public ResponseEntity<Nutrition> createNutrition(@RequestBody Nutrition nutrition, GymUser user) {
        return ResponseEntity.status(201).body(nutritionService.createNutrition(nutrition,user));
    }

    @GetMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Nutrition> getNutricion(@PathVariable Long id) {
        Nutrition nutrition = nutritionService.getNutrition(id);
        if (nutrition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nutrition);
    }
    @GetMapping("/all/{userId}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Collection<Nutrition>> allNutritions(@PathVariable Long userId) {
        return ResponseEntity.ok(nutritionService.getAll(userId));
    }

    @PutMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Nutrition> updateNutrition(@PathVariable Long id, @RequestBody Nutrition nutrition) {
        Nutrition update = nutritionService.updateNutrition(id, nutrition);
        if (update == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Void> deleteNutrition(@PathVariable Long id) {
        nutritionService.deleteNutrition(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Nutrition> updateParcialNutrition(@PathVariable Long id, @RequestBody Nutrition parcialNutrition) {
        Nutrition existed = nutritionService.getNutrition(id);
        if (existed == null){
            return ResponseEntity.notFound().build();
        }
        if (parcialNutrition.getName() != null) {
            existed.setName(parcialNutrition.getName());
        }
        if (parcialNutrition.getType() != null) {
            existed.setType(parcialNutrition.getType());
        }
        nutritionService.updateNutrition(id, existed);
        return ResponseEntity.ok(existed);
    }
}