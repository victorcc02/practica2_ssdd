package ssdd.practicaWeb.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.entities.Routine;
import ssdd.practicaWeb.service.RoutineService;
import ssdd.practicaWeb.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/api/routines")
public class RoutineRESTController {
    @Autowired
    private RoutineService routineService;
    @Autowired
    private UserService userService;
    interface DetailedView extends Routine.PublicRoutine, Routine.AsociationUserRoutine{}
    @GetMapping("/all/{userId}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Collection<Routine>> getAllRoutines(@PathVariable Long userId){
        return ResponseEntity.ok(routineService.getAllRoutines(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Routine> createRoutine(@RequestBody Routine routine , @PathVariable Long userId){
        GymUser user = userService.getGymUser(userId);
        return ResponseEntity.status(201).body(routineService.createRoutine(routine,user));
    }
    @GetMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Routine> getRoutine(@PathVariable Long id){
        Routine routine = routineService.getRoutine(id);
        if(routine == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(routine);
    }
    @PutMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Routine> updateRoutine(@PathVariable Long id, @RequestBody Routine routine){
        GymUser user = userService.getGymUser(id);
        Routine updated = routineService.updateRoutine(id,routine,user);
        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id){
        routineService.deleteRoutine(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    @JsonView(DetailedView.class)
    public ResponseEntity<Routine> patchRoutine(@PathVariable Long id, @RequestBody Routine parcialRoutine){
        GymUser user = userService.getGymUser(id);
        Routine routine = routineService.getRoutine(id);
        if (routine == null){
            return ResponseEntity.notFound().build();
        }
        if (parcialRoutine.getRoutineName() != null) {
            routine.setRoutineName(parcialRoutine.getRoutineName());
        }
        if (parcialRoutine.getIntensity() != null) {
            routine.setIntensity(parcialRoutine.getIntensity());
        }
        if (parcialRoutine.getDuration() != 0) {
            routine.setDuration(parcialRoutine.getDuration());
        }
        if(parcialRoutine.getExercises() != null) {
            routine.setExercises(parcialRoutine.getExercises());
        }
        if(parcialRoutine.getGoal() != null) {
            routine.setGoal(parcialRoutine.getGoal());
        }
        routineService.updateRoutine(id,routine, user);
        return ResponseEntity.ok(routine);
    }
}
