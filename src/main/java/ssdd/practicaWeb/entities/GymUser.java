package ssdd.practicaWeb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class GymUser {
    /*
    * Variables are marked with JsonView() and an interface.
    * In the RESTController we will use one interface or another
    * to print the values we want
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 1500000)
    private String userImage;
    private String username;
    private String password;
    private double weight;//Kg
    private double goalWeight;//kg
    private int height;//cm
    private String gender;
    private int age;
    private String morphology;
    private String caloricPhase;

    @OneToMany(mappedBy = "gymUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Nutrition> listNutrition;

    @OneToMany(mappedBy = "gymUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Routine> listRoutine;

    //Constructor, getters, setters
    //Other details are not compulsory but editable
    public GymUser(Long id, String username, String password) {
        this.userImage = "/images/emptyUser.png";
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public GymUser() {
        this.userImage = "/images/emptyUser.png";
    }

    public List<Nutrition> getListNutrition() {
        return listNutrition;
    }

    public void setListNutrition(List<Nutrition> listNutrition) {
        this.listNutrition = listNutrition;
    }

    public List<Routine> getListRoutine() {
        return listRoutine;
    }

    public void setListRoutine(List<Routine> listRoutine) {
        this.listRoutine = listRoutine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMorphology() {
        return morphology;
    }

    public void setMorphology(String morphology) {
        this.morphology = morphology;
    }

    public String getCaloricPhase() {
        return caloricPhase;
    }

    public void setCaloricPhase(String caloricPhase) {
        this.caloricPhase = caloricPhase;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
