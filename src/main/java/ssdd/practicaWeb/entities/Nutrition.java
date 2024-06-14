package ssdd.practicaWeb.entities;


import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;


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
        this.name = name;
        this.type = type;
        this.listFoods = foods;
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
}
