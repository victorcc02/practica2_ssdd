package ssdd.practicaWeb.entities;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class GymUserDTO {
    public interface PublicUser{}
    public interface DetailedUser{}
    @JsonView(PublicUser.class)
    private Long id;
    @JsonView(DetailedUser.class)
    private String userImage;
    @JsonView(PublicUser.class)
    private String username;
    @JsonView(DetailedUser.class)
    private String password;
    @JsonView(DetailedUser.class)
    private double weight;//Kg
    @JsonView(DetailedUser.class)
    private double goalWeight;//kg
    @JsonView(DetailedUser.class)
    private int height;//cm
    @JsonView(DetailedUser.class)
    private String gender;
    @JsonView(DetailedUser.class)
    private int age;
    @JsonView(DetailedUser.class)
    private String morphology;
    @JsonView(DetailedUser.class)
    private String caloricPhase;
    private List<Nutrition> listNutrition;
    private List<Routine> listRoutine;
    public GymUserDTO() {
    }
    public GymUserDTO(GymUser user) {
        this();
        this.setId(user.getId());
        this.setUserImage(user.getUserImage());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setWeight(user.getWeight());
        this.setGoalWeight(user.getGoalWeight());
        this.setHeight(user.getHeight());
        this.setGender(user.getGender());
        this.setAge(user.getAge());
        this.setMorphology(user.getMorphology());
        this.setCaloricPhase(user.getCaloricPhase());
        this.setListRoutine(user.getListRoutine());
        this.setListNutrition(user.getListNutrition());
    }

    public GymUserDTO(GymUser user, List<Nutrition> nutritions, List<Routine> routines) {
        this();
        this.setId(user.getId());
        this.setUserImage(user.getUserImage());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setWeight(user.getWeight());
        this.setGoalWeight(user.getGoalWeight());
        this.setHeight(user.getHeight());
        this.setGender(user.getGender());
        this.setAge(user.getAge());
        this.setMorphology(user.getMorphology());
        this.setCaloricPhase(user.getCaloricPhase());
        this.setListRoutine(routines);
        this.setListNutrition(nutritions);
    }

    //Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
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

    public GymUser cast(){
        GymUser user = new GymUser();
        user.setId(this.getId());
        user.setUserImage(this.getUserImage());
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        user.setWeight(this.getWeight());
        user.setGoalWeight(this.getGoalWeight());
        user.setHeight(this.getHeight());
        user.setGender(this.getGender());
        user.setAge(this.getAge());
        user.setMorphology(this.getMorphology());
        user.setCaloricPhase(this.getCaloricPhase());
        user.setListRoutine(this.getListRoutine());
        user.setListNutrition(this.getListNutrition());
        return  user;
    }
}
