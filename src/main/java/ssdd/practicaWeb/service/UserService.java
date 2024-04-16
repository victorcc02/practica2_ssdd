package ssdd.practicaWeb.service;

import org.springframework.stereotype.Service;
import ssdd.practicaWeb.entities.GymUser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final Map<Long, GymUser> userMap = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();
    public GymUser createUser(GymUser user){
        long id = nextId.incrementAndGet();
        user.setId(id);
        userMap.put(id,user);
        return user;
    }
    public GymUser getUser(Long id){
        return userMap.get(id);
    }
    public Collection<GymUser> getAllUsers(){
        return userMap.values();
    }
    public GymUser updateUser(Long id, GymUser user){
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
