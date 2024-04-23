package ssdd.practicaWeb.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class GymUser {
    public interface User{}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(User.class)
    private Long id;
    @Column(length = 1500000)
    @JsonView(User.class)
    private String userImage;
    @JsonView(User.class)
    private String username;
    @JsonView(User.class)
    private String password;
    @JsonView(User.class)
    private double weight;//Kg
    @JsonView(User.class)
    private double goalWeight;//kg
    @JsonView(User.class)
    private int height;//cm
    @JsonView(User.class)
    private String gender;
    @JsonView(User.class)
    private int age;
    @JsonView(User.class)
    private String morphology;
    @JsonView(User.class)
    private String caloricPhase;

    @OneToMany(mappedBy = "gymUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Nutrition> listNutrition = new ArrayList<>();

    @OneToMany(mappedBy = "gymUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Routine> listRoutine = new ArrayList<>();

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
