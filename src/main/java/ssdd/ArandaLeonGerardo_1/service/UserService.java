package ssdd.ArandaLeonGerardo_1.service;

import org.springframework.stereotype.Service;
import ssdd.ArandaLeonGerardo_1.entities.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final Map<Long, User> userMap = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();
    public User createUser(User user){
        long id = nextId.incrementAndGet();
        user.setId(id);
        userMap.put(id,user);
        return user;
    }
    public User getUser(Long id){
        return userMap.get(id);
    }
    public Collection<User> getAllUsers(){
        return userMap.values();
    }
    public User updateUser(Long id, User user){
        if(!userMap.containsKey(id)){
            return null;
        }
        user.setId(id);
        userMap.put(id,user);
        return user;
    }
    public void deleteUser(Long id){
        userMap.remove(id);
    }
}
