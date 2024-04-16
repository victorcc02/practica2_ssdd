package ssdd.practicaWeb.controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssdd.practicaWeb.entities.Food;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.entities.Nutrition;
import ssdd.practicaWeb.repositories.FoodRepository;
import ssdd.practicaWeb.service.FoodService;
import ssdd.practicaWeb.service.NutritionService;
import ssdd.practicaWeb.service.UserService;

@Controller
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private NutritionService nutritionService;

    @PostConstruct
    public void foodConstructor(){
        Food fresa = new Food("fresa","fruta",33);
        Food nueces = new Food("nueces","fruto seco",654);
        Food huevo = new Food("huevo","alimento proteico",155);
        Food lubina = new Food("lubina","pescado",98);
        Food yogurFresa = new Food("yogur fresa","lacteo",59);
        Food leche = new Food("leche","bebida",42);
        Food queso = new Food("queso","lacteo",402);
        Food naranja = new Food("naranja","fruta",47);
        Food pizza = new Food("pizza","pizza",266);
        Food limon = new Food("limon","citrico",29);
        Food  coliflor= new Food("coliflor","verdura",25);
        Food  aquarius= new Food("aquarius","bebida",45);
        Food  lechuga= new Food("lechuga","verdura",15);
        Food  yogurLimon= new Food("yogur limon","lacteo",103);
        Food  costillar= new Food("ribs","carne",395);

        foodService.createFood(fresa);
        foodService.createFood(nueces);
        foodService.createFood(huevo);
        foodService.createFood(lubina);
        foodService.createFood(yogurFresa);
        foodService.createFood(leche);
        foodService.createFood(queso);
        foodService.createFood(naranja);
        foodService.createFood(pizza);
        foodService.createFood(limon);
        foodService.createFood(coliflor);
        foodService.createFood(aquarius);
        foodService.createFood(lechuga);
        foodService.createFood(yogurLimon);
        foodService.createFood(costillar);
    }


    @GetMapping("/ListFoods")
    public String InterfaceListFood(@RequestParam Long id, Model model) {
        model.addAttribute("food", foodService.getAllFood());
        Nutrition nutrition = nutritionService.getNutrition(id);
        if (nutrition != null) {
            model.addAttribute("nutrition", nutrition);
            model.addAttribute("nutritionId", nutrition.getId());
            return "foodSearch";
        }
        return "redirect:/ListNutrition";
    }

    @GetMapping("/ListFoods/CreateFood")
    public String InterfaceCreateNutrition(@RequestParam Long id, Model model) {
        model.addAttribute("food",new Food());
        GymUser user = userService.getGymUser(id);
        if (user != null){
            model.addAttribute("userId",user.getId());
            return "createFood";
        }
        return "redirect:/ListNutrition";
    }
    @PostMapping("/ListFoods/CreateFood")
    public String addFood(Food food, @RequestParam("id") Long id){
        GymUser user = userService.getGymUser(id);
        if (user != null){
            foodService.createFood(food);
            return "redirect:/ListFoods?id=" + user.getId();
        }
        return "redirect:/FrontPage";

    }
    @GetMapping("/ListFoods/detailsFood/{id}")
    public String detailsFood(@PathVariable Long id, Model model){
        Food food = foodService.getFood(id);
        if (food == null) {
            return "redirect:/ListFoods";
        }
        model.addAttribute("food", food);
        if(food != null){
            return "detailsFood";
        }
        return "redirect:/ListFoods";
    }

    @GetMapping("/ListFoods/ModifyFood/{foodId}")
    public String showFormEdit(@PathVariable Long foodId, Model model, @RequestParam("id") Long userId) {
        Food food = foodService.getFood(foodId);
        if(food != null){
            model.addAttribute("food", food);
        }
        GymUser user = userService.getGymUser(userId);
        if(user != null){
            model.addAttribute("userId",user.getId());
            return "modifyFood";
        }
        return "redirect:/ListFoods";
    }
    @PostMapping("/ListFoods/ModifyFood/{foodId}")
    public String editFood(Food food,@PathVariable Long foodId,@RequestParam("id") Long id) {
        foodService.updateFood(foodId, food);
        GymUser user = userService.getGymUser(id);
        return "redirect:/ListFoods?id=" + user.getId();
    }
    @GetMapping("/ListFoods/DeleteFood/{id}")
    public String deleteFood(@PathVariable Long id, @RequestParam("id") Long userId) {
        foodService.deleteFood(id);
        GymUser user = userService.getGymUser(userId);
        return "redirect:/ListFoods?id=" + user.getId();
    }
}
