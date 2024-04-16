package ssdd.practicaWeb.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int calories;
    private String type;

    @ManyToMany
    @JoinTable(
            name = "food_nutrition",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "nutrition_id")
    )
    private Set<Nutrition> listNutritions = new HashSet<>();

    public Food(String name, String type, int calories) {
        this.name = name;
        this.type = type;
        this.calories = calories;
    }

    public Food() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Nutrition> getListNutritions() {
        return listNutritions;
    }

    public void setListNutritions(Set<Nutrition> listNutritions) {
        this.listNutritions = listNutritions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}