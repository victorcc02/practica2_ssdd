package ssdd.practicaWeb.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private GymUser gymUser;

    @ManyToMany(mappedBy = "listNutritions")
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