package ssdd.practicaWeb.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoutineDTO {
    private Long id;
    private String routineName;
    private String intensity;
    private int duration; // minutes
    private String exercises;
    private String goal; // Increase-Lose weight...
    private Long gymUserId;
    private String gymUserUsername;

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
        this.setGymUserId(routine.getGymUser().getId());
        this.setGymUserUsername(routine.getGymUser().getUsername());
    }

}
