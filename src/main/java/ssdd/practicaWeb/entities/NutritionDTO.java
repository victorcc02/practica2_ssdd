package ssdd.practicaWeb.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class NutritionDTO {
    private Long id;
    private String name;
    private String type;
    private Long gymUserId;
    private String gymUserUsername;
    private List<FoodView> listFoods;
    @Getter
    @Setter
    private class FoodView{
        private Long id;
        private String name;

        public FoodView(Food food) {
            this();
            this.setId(food.getId());
            this.setName(food.getName());
        }

        public FoodView() {
        }
    }

    public NutritionDTO() {
    }

    public NutritionDTO(Nutrition nutrition){
        this();
        this.setId(nutrition.getId());
        this.setName(nutrition.getName());
        this.setType(nutrition.getType());
        List<FoodView> list = new ArrayList<>();
        Set<Food> repeatedFoods = new HashSet<>();
        if(nutrition.getListFoods() != null){
            for(Food food: nutrition.getListFoods()){
                if(!repeatedFoods.contains(food)){
                    repeatedFoods.add(food);
                    list.add(new FoodView(food));
                }
            }
        }
        this.setListFoods(list);
        this.setGymUserId(nutrition.getGymUser().getId());
        this.setGymUserUsername(nutrition.getGymUser().getUsername());
    }
}
