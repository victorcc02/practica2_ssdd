package ssdd.ArandaLeonGerardo_1.entities;

public class User {
    private String userImage;
    private Long id;
    private String username;
    private String password;
    private double weight;//Kg
    private double goalWeight;//kg
    private int height;//cm
    private String gender;
    private int age;
    private String morphology;
    private String caloricPhase;

    //Constructor, getters, setters
    //Other details are not compulsory but editable
    public User(Long id, String username, String password) {
        this.userImage = "/imagenes/emptyUser.png";
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User() {
        this.userImage = "/imagenes/emptyUser.png";
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
