package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssdd.ArandaLeonGerardo_1.entities.Routine;
import ssdd.ArandaLeonGerardo_1.service.RoutineService;

import java.util.Collection;

@RestController
@RequestMapping("/api/routines")
public class RoutineRESTController {
    @Autowired
    private RoutineService routineService;
    @GetMapping
    public ResponseEntity<Collection<Routine>> getAllRoutines(){
        return ResponseEntity.ok(routineService.getAllRoutines());
    }
    @PostMapping
    public ResponseEntity<Routine> createRoutine(@RequestBody Routine routine){
        return ResponseEntity.status(201).body(routineService.createRoutine(routine));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Routine> getRoutine(@PathVariable Long id){
        Routine routine = routineService.getRoutine(id);
        if(routine == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(routine);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Routine> updateRoutine(@PathVariable Long id, @RequestBody Routine routine){
        Routine updated = routineService.updateRoutine(id,routine);
        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id){
        routineService.deleteRoutine(id);
        return ResponseEntity.ok().build();
    }
}
