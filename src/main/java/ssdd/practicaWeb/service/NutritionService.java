package ssdd.practicaWeb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssdd.practicaWeb.entities.Food;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.entities.Nutrition;
import ssdd.practicaWeb.repositories.FoodRepository;
import ssdd.practicaWeb.repositories.NutritionRepository;
import ssdd.practicaWeb.repositories.UserRepository;

import java.util.*;

@Service
public class NutritionService {

    @Autowired
    private NutritionRepository nutritionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;

    public Nutrition createNutrition(Nutrition nutrition, GymUser user) {
        Nutrition newNutrition = new Nutrition(nutrition.getName(),nutrition.getType());
        newNutrition.setGymUser(user);
        nutritionRepository.save(newNutrition);
        return newNutrition;
    }

    public Nutrition getNutrition(Long id) {
        Optional<Nutrition> theNutrition = nutritionRepository.findById(id);
        if (theNutrition.isPresent()) {
            Nutrition nutrition = theNutrition.get();
            return nutrition;
        } else {
            return null;
        }
    }

    public Collection<Nutrition> getAll(Long id) {
        List<Nutrition> listNutritionUser = nutritionRepository.findByGymUser(userService.getGymUser(id));
        return listNutritionUser;
    }

    public Nutrition updateNutrition(Long id, Nutrition nutrition) {
        Optional<Nutrition> theNutrition = nutritionRepository.findById(id);
        if(theNutrition.isPresent()) {
            nutrition.setId(id);
            nutritionRepository.save(nutrition);
            return nutrition;
        }
        return null;
    }

    public Nutrition deleteNutrition(Long id) {

        Optional<Nutrition> theNutrition = nutritionRepository.findById(id);

        if (theNutrition.isPresent()) {
            Nutrition nutrition = theNutrition.get();

            Collection<Food> foods = foodService.getAllFood();
            Iterator<Food> iterator = foods.iterator();
            while ((iterator.hasNext())){
                Food f = iterator.next();
                if(f.getListNutritions().contains(nutrition)) {
                    f.getListNutritions().remove(nutrition);
                }
            }
            GymUser user = nutrition.getGymUser();
            user.getListNutrition().remove(nutrition);
            nutritionRepository.delete(nutrition);
            return nutrition;
        }
        return null;
    }

    public void addFood (Nutrition nutrition, Food food){
        nutrition.getListFoods().add(food);
        food.getListNutritions().add(nutrition);
        nutritionRepository.save(nutrition);
        foodRepository.save(food);
    }
    public void deleteListFood (Nutrition nutrition, Food food){

        nutrition.getListFoods().remove(food);

        Collection<Food> foods  = foodService.getAllFood();
        Iterator<Food> iterator = foods.iterator();
        while ((iterator.hasNext())){
            Food f = iterator.next();
            if(f.getListNutritions().contains(nutrition)) {
                food.getListNutritions().remove(nutrition);
            }
        }
        nutritionRepository.save(nutrition);
    }
}
