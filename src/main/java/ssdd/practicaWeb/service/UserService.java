package ssdd.practicaWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.entities.GymUserDTO;
import ssdd.practicaWeb.entities.Nutrition;
import ssdd.practicaWeb.entities.Routine;
import ssdd.practicaWeb.repositories.NutritionRepository;
import ssdd.practicaWeb.repositories.RoutineRepository;
import ssdd.practicaWeb.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoutineRepository routineRepository;
    @Autowired
    NutritionRepository nutritionRepository;

    public GymUser createGymUser(GymUser gymUser){
        userRepository.save(gymUser);
        return gymUser;
    }

    public GymUser getGymUser(String username){
        Optional<GymUser> theUser = userRepository.findByUsername(username);
        if (theUser.isPresent()) {
            return theUser.get();
        }
        return null;
    }
    public GymUser getGymUser(Long id){
        Optional<GymUser> theUser = userRepository.findById(id);
        if (theUser.isPresent()) {
            return theUser.get();
        }
        return null;
    }
    public Collection <GymUser> getAllGymUser(){
        return userRepository.findAll();
    }
    public GymUserDTO updateGymUser(Long id, GymUser gymUser){
        Optional<GymUser> theGymUser = userRepository.findById(id);
        if(theGymUser.isPresent()) {
            gymUser.setId(id);
            userRepository.save(gymUser);
            List<Routine> routines = routineRepository.findByGymUser(gymUser);
            List<Nutrition> nutritions = nutritionRepository.findByGymUser(gymUser);
            return new GymUserDTO(gymUser,nutritions,routines);
        }
        return null;
    }
    public GymUserDTO deleteGymUser(Long id){
        Optional<GymUser> theGymUser = userRepository.findById(id);
        if (theGymUser.isPresent()) {
            GymUser user = theGymUser.get();
            List<Routine> routines = routineRepository.findByGymUser(user);
            List<Nutrition> nutritions = nutritionRepository.findByGymUser(user);
            userRepository.delete(user);
            return new GymUserDTO(user,nutritions,routines);
        }
        return null;
    }
}

