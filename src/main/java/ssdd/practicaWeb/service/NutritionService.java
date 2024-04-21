package ssdd.practicaWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.entities.Nutrition;
import ssdd.practicaWeb.repositories.NutritionRepository;
import ssdd.practicaWeb.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class NutritionService {

    @Autowired
    NutritionRepository nutritionRepository;

    @Autowired
    UserRepository userRepository;

    public Nutrition createNutrition(Nutrition nutrition, GymUser user) {
        List<Nutrition> newList = user.getListNutrition();
        newList.add(nutrition);
        user.setListNutrition(newList);
        userRepository.save(user);
        return nutrition;
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

    public Collection<Nutrition> getAll() {
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
            nutritionRepository.delete(nutrition);
            return nutrition;
        }
        return null;
    }
}
