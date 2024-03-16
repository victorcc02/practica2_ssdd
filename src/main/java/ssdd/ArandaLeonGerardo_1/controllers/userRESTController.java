package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssdd.ArandaLeonGerardo_1.entities.User;
import ssdd.ArandaLeonGerardo_1.service.UserService;

@RestController
@RequestMapping("/api/users")
public class userRESTController {
    @Autowired
    private UserService userService;

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
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
