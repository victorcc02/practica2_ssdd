package ssdd.practicaWeb.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FoodDTO {
    private Long id;
    private String name;
    private int calories;
    private String type;
    private List<NutritionView> listNutritions;
    @Getter
    @Setter
    private class NutritionView{
        private Long id;
        private String name;
        private String type;

        public NutritionView(Nutrition nutrition) {
            this();
            this.setId(nutrition.getId());
            this.setName(nutrition.getName());
            this.setType(nutrition.getType());
        }

        public NutritionView() {
        }
    }

    public FoodDTO() {
    }

    public FoodDTO(Food food){
        this();
        this.setId(food.getId());
        this.setName(food.getName());
        this.setCalories(food.getCalories());
        this.setType(food.getType());
        List<NutritionView> list = new ArrayList<>();
        if(food.getListNutritions() != null){
            for(Nutrition nutrition: food.getListNutritions()){
                list.add(new NutritionView(nutrition));
            }
        }
        this.setListNutritions(list);
    }
}
