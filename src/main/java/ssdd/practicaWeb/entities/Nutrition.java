package ssdd.practicaWeb.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;


@Setter
@Getter
@Entity
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private GymUser gymUser;
    @ManyToMany(mappedBy = "listNutritions", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Food> listFoods;

    @PreRemove
    private void deleteReferences(){
        for(Food food: listFoods){
            if(food.getListNutritions() != null){
                food.getListNutritions().remove(this);
            }
        }
    }

    public Nutrition() {
    }

    public Nutrition(String name , String type, List<Food> foods) {
        if(name != null){
            this.name = name;
        }else{
            this.name = "Not specified";
        }
        if(type != null){
            this.type = type;
        }else{
            this.type = "Not specified";
        }
        this.listFoods = foods;
    }

}
