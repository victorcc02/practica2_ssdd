package ssdd.practicaWeb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssdd.practicaWeb.entities.Food;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.entities.Nutrition;
import ssdd.practicaWeb.entities.Routine;
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
                if(aux.isEmpty()){
                    return null;
                }
            }
            for(Food food: nutrition.getListFoods()){
                Optional<Food> aux = foodRepository.findByName(food.getName());
                if(aux.isPresent()){
                    if(newNutrition.getListFoods() != null){
                        if(!newNutrition.getListFoods().contains(aux.get())){
                            addFood(newNutrition,aux.get());
                        }
                    }
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
            resetListFood(theNutrition.get());
            nutrition.setGymUser(user);
            nutrition.setId(nutritionId);
            List<Food> list = nutrition.getListFoods();
            for(Food food: list){
                Food f = foodService.getFood(food.getName());
                if(f == null){
                    return null;
                }
            }
            nutrition.setListFoods(new ArrayList<>());
            for(Food food: list){
                Food f = foodService.getFood(food.getName());
                if(!nutrition.getListFoods().contains(f)){
                    addFood(nutrition, f);
                }
            }
            nutritionRepository.save(nutrition);
            return nutrition;
        }
        return null;
    }

    private void resetListFood(Nutrition nutrition){
        if(nutrition.getListFoods() != null){
            List<Food> list = nutrition.getListFoods();
            List<Food> tempList = new ArrayList<>(list);
            for (Food food : tempList) {
                deleteListFood(nutrition, food);
            }
            list.clear();
        }
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
            food = new Food(food.getName(),"Not specified",0);
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

    public void deleteNotAsociatedNutritions(List<Nutrition> nutritions, GymUser user){
        List<Nutrition> nutritionsAsociated = user.getListNutrition();
        List<Long> nutritionsToDelete = new ArrayList<>();
        if(nutritions != null && nutritionsAsociated != null){
            for(Nutrition nutrition: nutritionsAsociated){
                if(!nutritions.contains(nutrition)){
                    nutritionsToDelete.add(nutrition.getId());
                }
            }
            for(Long nutritionId: nutritionsToDelete){
                deleteNutrition(nutritionId);
            }
        }
    }

    public void deleteNotAsociatedFoods(List<Food> foods, Nutrition nutrition){
        List<Food> foodsAsociated = nutrition.getListFoods();
        List<Food> foodsToDelete = new ArrayList<>();
        if(foods!= null && foodsAsociated != null){
            for(Food food: foodsAsociated){
                if(!foods.contains(food)){
                    foodsToDelete.add(food);
                }
            }
            for(Food f: foodsToDelete){
                deleteListFood(nutrition, f);
            }
        }
    }
}
