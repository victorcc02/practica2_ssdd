package ssdd.practicaWeb.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FoodDTO {
    public interface PublicFood{}
    public interface AsociationFoodNutrition extends NutritionDTO.PublicNutrition{}
    @JsonView(PublicFood.class)
    private Long id;
    @JsonView(PublicFood.class)
    private String name;
    @JsonView(PublicFood.class)
    private int calories;
    @JsonView(PublicFood.class)
    private String type;
    @JsonView(AsociationFoodNutrition.class)
    private List<Nutrition> listNutritions;

    public FoodDTO() {
    }

    public FoodDTO(Food food){
        this();
        this.setId(food.getId());
        this.setName(food.getName());
        this.setCalories(food.getCalories());
        this.setType(food.getType());
        this.setListNutritions(food.getListNutritions());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Nutrition> getListNutritions() {
        return listNutritions;
    }

    public void setListNutritions(List<Nutrition> listNutritions) {
        this.listNutritions = listNutritions;
    }
}
