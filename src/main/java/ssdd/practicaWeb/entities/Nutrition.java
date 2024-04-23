package ssdd.practicaWeb.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
public class Nutrition {
    public interface PublicNutrition{}
    public interface AsociationUserNutrition{}
    public interface ListFood{}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PublicNutrition.class)
    private Long id;
    @JsonView(PublicNutrition.class)
    private String name;
    @JsonView(PublicNutrition.class)
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonView(AsociationUserNutrition.class)
    private GymUser gymUser;

    @ManyToMany(mappedBy = "listNutritions")
    @JsonView(ListFood.class)
    private Set<Food> listFoods = new HashSet<>();

    public Nutrition() {
    }

    public Nutrition(String name , String type) {
        this.name = name;
        this.type = type;
    }

    public GymUser getGymUser() {
        return gymUser;
    }

    public void setGymUser(GymUser gymUser) {
        this.gymUser = gymUser;
    }

    public Set<Food> getListFoods() {
        return listFoods;
    }

    public void setListFoods(Set<Food> listFoods) {
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
