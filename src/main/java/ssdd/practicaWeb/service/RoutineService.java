package ssdd.practicaWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssdd.practicaWeb.entities.Routine;
import ssdd.practicaWeb.repositories.RoutineRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class RoutineService {

    @Autowired
    RoutineRepository routineRepository;

    public Routine createRoutine(Routine routine){
        routineRepository.save(routine);
        return routine;
    }
    public Routine getRoutine(Long id){
        Optional<Routine> theRoutine = routineRepository.findById(id);
        if (theRoutine.isPresent()) {
            Routine routine = theRoutine.get();
            return routine;
        } else {
            return null;
        }
    }
    public Collection <Routine> getAllRoutines(){
        return routineRepository.findAll();
    }
    public Routine updateRoutine(Long id, Routine routine){
        Optional<Routine> theRoutine = routineRepository.findById(id);
        if(theRoutine.isPresent()) {
            routine.setId(id);
            routineRepository.save(routine);
            return routine;
        }

        return null;
    }
    public Routine deleteRoutine(Long id){
        Optional<Routine> theRoutine = routineRepository.findById(id);
        if (theRoutine.isPresent()) {
            Routine routine = theRoutine.get();
            routineRepository.delete(routine);
            return routine;
        }
        return null;
    }
}
