package ssdd.practicaWeb.entities;


import jakarta.persistence.*;

@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String routineName;
    private String intensity;
    private int duration; // minutes
    private String exercises;
    private String goal; // Increase-Lose weight...
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private GymUser gymUser;

    public Routine(String routineName, String intensity, int duration, String exercises, String goal) {
        if(routineName != null){
            this.routineName = routineName;
        }else{
            this.routineName = "Not specified";
        }
        if(intensity != null){
            this.intensity = intensity;
        }else{
            this.intensity = "Not specified";
        }
        this.duration = duration;
        if(exercises != null){
            this.exercises = exercises;
        }else{
            this.exercises = "Not specified";
        }
        if(goal != null){
            this.goal = goal;
        }else{
            this.goal = "Not specified";
        }
    }

    public Routine() {
    }

    public GymUser getGymUser() {
        return gymUser;
    }

    public void setGymUser(GymUser gymUser) {
        this.gymUser = gymUser;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }
}
