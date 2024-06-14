package ssdd.practicaWeb.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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
        if(nutrition.getListFoods() != null){
            for(Food food: nutrition.getListFoods()){
                list.add(new FoodView(food));
            }
        }
        this.setListFoods(list);
        this.setGymUserId(nutrition.getGymUser().getId());
        this.setGymUserUsername(nutrition.getGymUser().getUsername());
    }
}
