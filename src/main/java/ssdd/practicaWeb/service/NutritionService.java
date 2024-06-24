package ssdd.practicaWeb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssdd.practicaWeb.entities.Food;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.entities.Nutrition;
import ssdd.practicaWeb.repositories.FoodRepository;
import ssdd.practicaWeb.repositories.NutritionRepository;

import java.util.*;

@Service
public class NutritionService {

    @Autowired
    private NutritionRepository nutritionRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;

    public Nutrition createNutrition(Nutrition nutrition, GymUser user) {
        Nutrition newNutrition = new Nutrition(nutrition.getName(),nutrition.getType(),new ArrayList<>());
        if(nutrition.getListFoods() != null){
            for(Food food: nutrition.getListFoods()){
                Optional<Food> aux = foodRepository.findByName(food.getName());
                if(aux.isPresent()){
                    addFood(newNutrition,aux.get());
                }else{
                    food = new Food(food.getName(),food.getType(),0);
                    food.setListNutritions(new ArrayList<>());
                    Food newFood = foodService.createFood(food);
                    addFood(newNutrition,newFood);
                }
            }
        }
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
        Optional<List<Nutrition>> listNutritionUser = nutritionRepository.findByGymUser(userService.getGymUser(id));
        if(listNutritionUser.isPresent()){
            return listNutritionUser.get();
        }
        return null;
    }

    public Nutrition updateNutrition(Long nutritionId, Nutrition nutrition, GymUser user) {
        Optional<Nutrition> theNutrition = nutritionRepository.findById(nutritionId);
        if(theNutrition.isPresent()) {
            nutrition.setGymUser(user);
            nutrition.setId(nutritionId);
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
        Optional<Food> optionalFood = foodRepository.findByName(food.getName());
        if(optionalFood.isEmpty()){
            food = new Food(food.getName(),null,0);
            food.setListNutritions(new ArrayList<>());
        }
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
    public List<Nutrition> getNutritionsUser(GymUser user){
        Optional<List<Nutrition>> nutritions = nutritionRepository.findByGymUser(user);
        if(nutritions.isPresent()){
            return nutritions.get();
        }
        return null;
    }
}
