package ssdd.ArandaLeonGerardo_1.entities;


import java.util.List;

public class Routine {
    private String intensity;
    private int duration; // minutes

    private List<String> exercises;

    private String goal; // Increase-Lose weight...

    public Routine(String intensity, int duration, List<String> exercises, String goal) {
        this.intensity = intensity;
        this.duration = duration;
        this.exercises = exercises;
        this.goal = goal;
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

    public List<String> getExercises() {
        return exercises;
    }

    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
