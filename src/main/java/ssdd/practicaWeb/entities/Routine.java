package ssdd.practicaWeb.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

@Entity
public class Routine {
    public interface PublicRoutine{}
    public interface AsociationUserRoutine{}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PublicRoutine.class)
    private Long id;
    @JsonView(PublicRoutine.class)
    private String routineName;
    @JsonView(PublicRoutine.class)
    private String intensity;
    @JsonView(PublicRoutine.class)
    private int duration; // minutes
    @JsonView(PublicRoutine.class)
    private String exercises;
    @JsonView(PublicRoutine.class)
    private String goal; // Increase-Lose weight...
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonView(AsociationUserRoutine.class)
    private GymUser gymUser;

    public Routine(String routineName, String intensity, int duration, String exercises, String goal) {
        this.routineName = routineName;
        this.intensity = intensity;
        this.duration = duration;
        this.exercises = exercises;
        this.goal = goal;
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
