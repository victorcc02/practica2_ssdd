package ssdd.practicaWeb.controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssdd.practicaWeb.entities.Nutrition;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.repositories.NutritionRepository;
import ssdd.practicaWeb.service.NutritionService;
import ssdd.practicaWeb.service.UserService;

@Controller
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;
    @Autowired
    private UserService userService;
    @Autowired
    NutritionRepository nutritionRepository;

/*  @PostConstruct
    public void nutritionConstructor(){
        Nutrition nutritionVolume = new Nutrition("Nutricion 1","volumen",);
        Nutrition nutritionSlim = new Nutrition("Nutricion 2","adelgazar",);
        Nutrition nutritionGym = new Nutrition("Nutricion 3","proteinas",);

        nutritionService.createNutrition(nutritionVolume);
        nutritionService.createNutrition(nutritionSlim);
        nutritionService.createNutrition(nutritionGym);
    }*/


    @GetMapping("/Nutrition")
    public String InterfaceNutrition(@RequestParam Long id, Model model) {
        GymUser user = userService.getGymUser(id);
        if (user != null){
            model.addAttribute("userId",user.getId());
            return "nutrition";
        }
        return "redirect:/FrontPage";
    }
    @GetMapping("/ListNutrition")
    public String InterfaceListNutrition(@RequestParam Long id, Model model) {
        model.addAttribute("nutrition", nutritionService.getAll());
        GymUser user = userService.getGymUser(id);
        if (user != null){
            model.addAttribute("userId",user.getId());
            return "listNutrition";
        }
        return "redirect:/Nutrition";
    }
    @GetMapping("/ListNutrition/CreateNutrition")
    public String InterfaceCreateNutrition(@RequestParam Long id, Model model) {
        model.addAttribute("nutricion",new Nutrition());
        GymUser user = userService.getGymUser(id);
        if (user != null){
            model.addAttribute("userId",user.getId());
            return "createNutrition";
        }
        return "redirect:/FrontPage";
    }
    @PostMapping("/ListNutrition/CreateNutrition")
    public String addNutrition(Nutrition nutrition, @RequestParam("id") Long id){
        GymUser user = userService.getGymUser(id);
        if (user != null){
            nutrition.setGymUser(user);
            nutritionService.createNutrition(nutrition, user);
            //return "redirect:/ListNutrition?id=" + user.getId();
            return "redirect:/ListFoods?id=" + nutrition.getId();
        }
        return "redirect:/FrontPage";

    }
    @GetMapping("/ListNutrition/detailsNutrition/{id}")
    public String detailsNutrition(@PathVariable Long id, Model model, @RequestParam("id") Long userId){
        Nutrition nutrition = nutritionService.getNutrition(id);
        if (nutrition == null) {
            return "redirect:/ListNutrition";
        }
        model.addAttribute("nutrition", nutrition);
        GymUser user = userService.getGymUser(userId);
        if(user != null){
            model.addAttribute("userId",user.getId());
            return "detailsNutrition";
        }
        return "redirect:/ListNutrition";
    }

    @GetMapping("/ListNutrition/ModifyNutrition/{nutritionId}")
    public String showFormEdit(@PathVariable Long nutritionId, Model model, @RequestParam("id") Long userId) {
        Nutrition nutrition = nutritionService.getNutrition(nutritionId);
        if(nutrition != null){
            model.addAttribute("nutrition", nutrition);
        }
        GymUser user = userService.getGymUser(userId);
        if(user != null){
            model.addAttribute("userId",user.getId());
            return "modifyNutrition";
        }
        return "redirect:/ListNutrition";
    }
    @PostMapping("/ListNutrition/ModifyNutrition/{nutritionId}")
    public String editNutrition(Nutrition nutrition,@PathVariable Long nutritionId,@RequestParam("id") Long id) {
        nutritionService.updateNutrition(nutritionId, nutrition);
        GymUser user = userService.getGymUser(id);
        return "redirect:/ListNutrition?id=" + user.getId();
    }
    @GetMapping("/ListNutrition/DeleteNutrition/{id}")
    public String deleteNutrition(@PathVariable Long id, @RequestParam("id") Long userId) {
        nutritionService.deleteNutrition(id);
        GymUser user = userService.getGymUser(userId);
        return "redirect:/ListNutrition?id=" + user.getId();
    }
}


