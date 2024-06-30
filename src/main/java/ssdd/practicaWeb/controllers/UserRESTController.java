package ssdd.practicaWeb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssdd.practicaWeb.entities.*;
import ssdd.practicaWeb.service.NutritionService;
import ssdd.practicaWeb.service.RoutineService;
import ssdd.practicaWeb.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRESTController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoutineService routineService;
    @Autowired
    private NutritionService nutritionService;
    @GetMapping
    public ResponseEntity<Collection<GymUserDTO>> getAllUsers(){
        List<GymUserDTO> list = new ArrayList<>();
        List<GymUser> gymUsers = (List<GymUser>) userService.getAllGymUser();
        for(GymUser user: gymUsers){
            List<Routine> routines = routineService.getRoutinesUser(user);
            List<Nutrition> nutritions = nutritionService.getNutritionsUser(user);
            list.add(new GymUserDTO(user,nutritions,routines));
        }
        return ResponseEntity.ok(list);
    }
    @PostMapping
    public ResponseEntity<GymUserDTO> createUser(@RequestBody GymUser user){
        GymUser userObt = userService.createGymUser(user);
        if(user.getListRoutine() != null){
            if(!routineExistenceVerification(user.getListRoutine())){
                return ResponseEntity.notFound().build();
            }
            List<Routine> newRoutines = new ArrayList<>();
            for(Routine routine: user.getListRoutine()){
                Routine r = routineService.getRoutine(routine.getId());
                //Routine could be owned by other users
                if(r.getGymUser().getId() == user.getId()){
                    newRoutines.add(r);
                }
            }
            routineService.deleteNotAsociatedRoutines(newRoutines, userObt);
            userObt.setListRoutine(newRoutines);
        }
        if(user.getListNutrition() != null){
            if(!nutritionExistenceVerification(user.getListNutrition())){
                return ResponseEntity.notFound().build();
            }
            List<Nutrition> newNutritions = new ArrayList<>();
            for(Nutrition nutrition: user.getListNutrition()){
                Nutrition n = nutritionService.getNutrition(nutrition.getId());
                if(n.getGymUser().getId() == user.getId()){
                    newNutritions.add(n);
                }
            }
            nutritionService.deleteNotAsociatedNutritions(newNutritions, userObt);
            userObt.setListNutrition(newNutritions);
        }
        return ResponseEntity.status(201).body(new GymUserDTO(userObt));
    }
    @GetMapping("/{id}")
    public ResponseEntity<GymUserDTO> getUser(@PathVariable Long id){
        GymUser user = userService.getGymUser(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Routine> routines = routineService.getRoutinesUser(user);
        List<Nutrition> nutritions = nutritionService.getNutritionsUser(user);
        return ResponseEntity.ok(new GymUserDTO(user,nutritions,routines));
    }
    @PutMapping("/{id}")
    public ResponseEntity<GymUserDTO> updateUser(@PathVariable Long id, @RequestBody GymUser user){
        GymUser updated = userService.updateGymUser(id,user);
        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        List<Routine> routines = routineService.getRoutinesUser(user);
        List<Nutrition> nutritions = nutritionService.getNutritionsUser(user);
        return ResponseEntity.ok(new GymUserDTO(updated,nutritions,routines));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        GymUser user = userService.getGymUser(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        userService.deleteGymUser(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<GymUserDTO> patchUser(@PathVariable Long id, @RequestBody GymUser parcialUser){
        GymUser user = userService.getGymUser(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        if(parcialUser.getUserImage() != null){
            user.setUserImage(parcialUser.getUserImage());
        }
        if(parcialUser.getUsername() != null){
            user.setUsername(parcialUser.getUsername());
        }
        if(parcialUser.getPassword() != null){
            user.setPassword(parcialUser.getPassword());
        }
        if(parcialUser.getWeight() != 0){
            user.setWeight(parcialUser.getWeight());
        }
        if(parcialUser.getGoalWeight() != 0){
            user.setGoalWeight(parcialUser.getGoalWeight());
        }
        if(parcialUser.getHeight() != 0){
            user.setHeight(parcialUser.getHeight());
        }
        if(parcialUser.getGender() != null){
            user.setGender(parcialUser.getGender());
        }
        if(parcialUser.getAge() != 0){
            user.setAge(parcialUser.getAge());
        }
        if(parcialUser.getMorphology() != null){
            user.setMorphology(parcialUser.getMorphology());
        }
        if(parcialUser.getCaloricPhase() != null){
            user.setCaloricPhase(parcialUser.getCaloricPhase());
        }
        if(parcialUser.getListRoutine() != null){
            if(!routineExistenceVerification(parcialUser.getListRoutine())){
                return ResponseEntity.notFound().build();
            }
            List<Routine> newRoutines = new ArrayList<>();
            for(Routine routine: parcialUser.getListRoutine()){
                Routine r = routineService.getRoutine(routine.getId());
                //Routine could be owned by other users
                if(r.getGymUser().getId() == user.getId()){
                    newRoutines.add(r);
                }
            }
            routineService.deleteNotAsociatedRoutines(newRoutines, user);
            user.setListRoutine(newRoutines);
        }
        if(parcialUser.getListNutrition() != null){
            if(!nutritionExistenceVerification(parcialUser.getListNutrition())){
                return ResponseEntity.notFound().build();
            }
            List<Nutrition> newNutritions = new ArrayList<>();
            for(Nutrition nutrition: parcialUser.getListNutrition()){
                Nutrition n = nutritionService.getNutrition(nutrition.getId());
                if(n.getGymUser().getId() == user.getId()){
                    newNutritions.add(n);
                }
            }
            nutritionService.deleteNotAsociatedNutritions(newNutritions, user);
            user.setListNutrition(newNutritions);
        }
        userService.updateGymUser(id,user);
        return ResponseEntity.ok(new GymUserDTO(user));
    }

    private boolean routineExistenceVerification(List<Routine> routines){
        for(Routine routine: routines){
            Routine r = routineService.getRoutine(routine.getId());
            if(r == null){
                return false;
            }
        }
        return true;
    }

    private boolean nutritionExistenceVerification(List<Nutrition> nutritions){
        for(Nutrition nutrition: nutritions){
            Nutrition n = nutritionService.getNutrition(nutrition.getId());
            if(n == null){
                return false;
            }
        }
        return true;
    }
}