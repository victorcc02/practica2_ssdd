package ssdd.practicaWeb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserRESTController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<Collection<GymUser>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllGymUser());
    }
    @PostMapping
    public ResponseEntity<GymUser> createUser(@RequestBody GymUser user){
        return ResponseEntity.status(201).body(userService.createGymUser(user));
    }
    @GetMapping("/{id}")
    public ResponseEntity<GymUser> getUser(@PathVariable Long id){
        GymUser user = userService.getGymUser(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GymUser> updateUser(@PathVariable Long id, @RequestBody GymUser user){
        GymUser updated = userService.updateGymUser(id,user);
        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteGymUser(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<GymUser> patchUser(@PathVariable Long id, @RequestBody GymUser parcialUser){
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
        userService.updateGymUser(id,user);
        return ResponseEntity.ok(user);
    }
}