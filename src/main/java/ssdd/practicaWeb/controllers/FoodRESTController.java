package ssdd.practicaWeb.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssdd.practicaWeb.entities.Food;
import ssdd.practicaWeb.service.FoodService;

import java.util.Collection;

@Controller
@RequestMapping("/api/food")
public class FoodRESTController {

    @Autowired
    private FoodService foodService;
    interface DetailedView extends Food.PublicFood, Food.AsociationFoodNutrition{}

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        return ResponseEntity.status(201).body(foodService.createFood(food));
    }

    @GetMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Food> getFood(@PathVariable Long id) {
        Food food = foodService.getFood(id);
        if (food == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(food);
    }

    @GetMapping
    @JsonView(DetailedView.class)
    public ResponseEntity<Collection<Food>> allFoods() {
        return ResponseEntity.ok(foodService.getAllFood());
    }

    @PutMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food food) {
        Food update = foodService.updateFood(id, food);
        if (update == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Food> updateParcialFood(@PathVariable Long id, @RequestBody Food parcialFood) {
        Food existed = foodService.getFood(id);
        if (existed == null){
            return ResponseEntity.notFound().build();
        }
        if (parcialFood.getName() != null) {
            existed.setName(parcialFood.getName());
        }
        if (parcialFood.getType() != null) {
            existed.setType(parcialFood.getType());
        }
        if (parcialFood.getCalories() != 0) {
            existed.setCalories(parcialFood.getCalories());
        }
        foodService.updateFood(id, existed);
        return ResponseEntity.ok(existed);
    }
}
