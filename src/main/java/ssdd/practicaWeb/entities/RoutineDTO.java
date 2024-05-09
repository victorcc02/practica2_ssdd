package ssdd.practicaWeb.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

public class RoutineDTO {
    public interface PublicRoutine{}
    public interface AsociationUserRoutine extends GymUserDTO.PublicUser{}
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
    @JsonView(AsociationUserRoutine.class)
    private GymUser gymUser;

    public RoutineDTO() {
    }
    public RoutineDTO(Routine routine){
        this();
        this.setId(routine.getId());
        this.setRoutineName(routine.getRoutineName());
        this.setIntensity(routine.getIntensity());
        this.setDuration(routine.getDuration());
        this.setExercises(routine.getExercises());
        this.setGoal(routine.getGoal());
        this.setGymUser(routine.getGymUser());
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

    public GymUser getGymUser() {
        return gymUser;
    }

    public void setGymUser(GymUser gymUser) {
        this.gymUser = gymUser;
    }
}
