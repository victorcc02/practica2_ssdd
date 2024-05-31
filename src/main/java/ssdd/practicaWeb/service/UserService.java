package ssdd.practicaWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssdd.practicaWeb.entities.Food;
import ssdd.practicaWeb.entities.GymUser;

import ssdd.practicaWeb.entities.Nutrition;
import ssdd.practicaWeb.entities.Routine;
import ssdd.practicaWeb.repositories.FoodRepository;
import ssdd.practicaWeb.repositories.NutritionRepository;
import ssdd.practicaWeb.repositories.RoutineRepository;
import ssdd.practicaWeb.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private NutritionRepository nutritionRepository;
    @Autowired
    private FoodRepository foodRepository;

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
            GymUser user = theGymUser.get();
            //Routine cascade delete
            for(Routine routine: new ArrayList<>(user.getListRoutine())){
                user.getListRoutine().remove(routine);
                routineRepository.delete(routine);
            }
            //Nutrition cascade delete
            ArrayList<Food> foods = (ArrayList<Food>) foodRepository.findAll();
            for(Nutrition nutrition: new ArrayList<>(user.getListNutrition())){
                for(Food food: foods){
                        food.getListNutritions().remove(nutrition);
                }
                user.getListNutrition().remove(nutrition);
                nutritionRepository.delete(nutrition);
            }
            userRepository.delete(user);
            return user;
        }
        return null;
    }
}

