package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssdd.ArandaLeonGerardo_1.entities.User;
import ssdd.ArandaLeonGerardo_1.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserRESTController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(201).body(userService.createUser(user));
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User updated = userService.updateUser(id,user);
        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody User parcialUser){
        User user = userService.getUser(id);
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
        userService.updateUser(id,user);
        return ResponseEntity.ok(user);
    }
}
