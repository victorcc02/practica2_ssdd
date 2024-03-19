package ssdd.practicaWeb.service;

import org.springframework.stereotype.Service;
import ssdd.practicaWeb.entities.Routine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RoutineService {
    private final Map<Long, Routine> routineMap = new HashMap<>();
    private final AtomicLong  nextId = new AtomicLong();
    public Routine createRoutine(Routine routine){
        long id = nextId.incrementAndGet();
        routine.setId(id);
        routineMap.put(id,routine);
        return routine;
    }
    public Routine getRoutine(Long id){
        return routineMap.get(id);
    }
    public Collection <Routine> getAllRoutines(){
        return routineMap.values();
    }
    public Routine updateRoutine(Long id, Routine routine){
        if(!routineMap.containsKey(id)){
            return null;
        }
        routine.setId(id);
        routineMap.put(id,routine);
        return routine;
    }
    public void deleteRoutine(Long id){
        routineMap.remove(id);
    }
}
