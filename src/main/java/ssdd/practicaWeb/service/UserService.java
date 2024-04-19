package ssdd.practicaWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public GymUser createGymUser(GymUser gymUser){
        userRepository.save(gymUser);
        return gymUser;
    }
    public GymUser getGymUser(Long id){
        Optional<GymUser> theUser = userRepository.findById(id);
        if (theUser.isPresent()) {
            GymUser gymUser = theUser.get();
            return gymUser;
        } else {
            return null;
        }
    }
    public Collection <GymUser> getAllGymUser(){
        return userRepository.findAll();
    }
    public GymUser updateGymUser(Long id, GymUser gymUser){
        Optional<GymUser> theGymUser = userRepository.findById(id);
        if(theGymUser.isPresent()) {
            gymUser.setId(id);
            userRepository.save(gymUser);
            return gymUser;
        }

        return null;
    }
    public GymUser deleteGymUser(Long id){
        Optional<GymUser> theGymUser = userRepository.findById(id);
        if (theGymUser.isPresent()) {
            GymUser gymUser = theGymUser.get();
            userRepository.delete(gymUser);
            return gymUser;
        }
        return null;
    }
}

