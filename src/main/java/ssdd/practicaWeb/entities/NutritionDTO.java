package ssdd.practicaWeb.entities;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class NutritionDTO {
    public interface PublicNutrition{}
    public interface AsociationUserNutrition extends GymUserDTO.PublicUser{}
    public interface ListFood extends FoodDTO.PublicFood{}
    @JsonView(PublicNutrition.class)
    private Long id;
    @JsonView(PublicNutrition.class)
    private String name;
    @JsonView(PublicNutrition.class)
    private String type;
    @JsonView(AsociationUserNutrition.class)
    private GymUser gymUser;
    @JsonView(ListFood.class)
    private List<Food> listFoods;

    public NutritionDTO() {
    }

    public NutritionDTO(Nutrition nutrition){
        this();
        this.setId(nutrition.getId());
        this.setName(nutrition.getName());
        this.setType(nutrition.getType());
        this.setGymUser(nutrition.getGymUser());
        this.setListFoods(nutrition.getListFoods());
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GymUser getGymUser() {
        return gymUser;
    }

    public void setGymUser(GymUser gymUser) {
        this.gymUser = gymUser;
    }

    public List<Food> getListFoods() {
        return listFoods;
    }

    public void setListFoods(List<Food> listFoods) {
        this.listFoods = listFoods;
    }
}
