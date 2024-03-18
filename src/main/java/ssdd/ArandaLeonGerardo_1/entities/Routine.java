package ssdd.ArandaLeonGerardo_1.entities;


import java.util.List;

public class Routine {
    private String routineName;
    private Long id;
    private String intensity;
    private int duration; // minutes
    private String exercises;
    private String goal; // Increase-Lose weight...


    public Routine(String routineName, Long id, String intensity, int duration, String exercises, String goal) {
        this.routineName = routineName;
        this.id = id;
        this.intensity = intensity;
        this.duration = duration;
        this.exercises = exercises;
        this.goal = goal;
    }

    public Routine() {
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

    public String getroutineName() {
        return routineName;
    }

    public void setroutineName(String name) {
        this.routineName = routineName;
    }
}
