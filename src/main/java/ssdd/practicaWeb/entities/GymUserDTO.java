package ssdd.practicaWeb.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class GymUserDTO {
    private Long id;
    private String userImage;
    private String username;
    private String password;
    private double weight;//Kg
    private double goalWeight;//kg
    private int height;//cm
    private String gender;
    private int age;
    private String morphology;
    private String caloricPhase;
    private List<NutritionView> listNutrition;
    private List<RoutineView> listRoutine;

    @Getter
    @Setter
    private class NutritionView{
        private Long id;
        private String name;
        private String type;

        public NutritionView(Nutrition nutrition) {
            this();
            this.setId(nutrition.getId());
            this.setName(nutrition.getName());
            this.setType(nutrition.getType());
        }

        public NutritionView() {
        }
    }
    @Getter
    @Setter
    private class RoutineView{
        private Long id;
        private String routineName;

        public RoutineView(Routine routine) {
            this();
            this.setId(routine.getId());
            this.setRoutineName(routine.getRoutineName());
        }

        public RoutineView() {
        }
    }

    public GymUserDTO() {
    }
    public GymUserDTO(GymUser user) {
        this();
        this.setId(user.getId());
        this.setUserImage(user.getUserImage());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setWeight(user.getWeight());
        this.setGoalWeight(user.getGoalWeight());
        this.setHeight(user.getHeight());
        this.setGender(user.getGender());
        this.setAge(user.getAge());
        this.setMorphology(user.getMorphology());
        this.setCaloricPhase(user.getCaloricPhase());
        List<RoutineView> listRoutine = new ArrayList<>();
        Set<Routine> repeatedRoutines = new HashSet<>();
        if(user.getListRoutine() != null){
            for(Routine routine: user.getListRoutine()) {
                if(!repeatedRoutines.contains(routine)){
                    repeatedRoutines.add(routine);
                    listRoutine.add(new RoutineView(routine));
                }
            }
        }
        this.setListRoutine(listRoutine);
        List<NutritionView> listNutrition = new ArrayList<>();
        Set<Nutrition> repeatedNutritions = new HashSet<>();
        if(user.getListNutrition() != null){
            for(Nutrition nutrition: user.getListNutrition()){
                if(!repeatedNutritions.contains(nutrition)){
                    repeatedNutritions.add(nutrition);
                    listNutrition.add(new NutritionView(nutrition));
                }
            }
        }
        this.setListNutrition(listNutrition);
    }

    public GymUserDTO(GymUser user, List<Nutrition> nutritions, List<Routine> routines) {
        this();
        this.setId(user.getId());
        this.setUserImage(user.getUserImage());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setWeight(user.getWeight());
        this.setGoalWeight(user.getGoalWeight());
        this.setHeight(user.getHeight());
        this.setGender(user.getGender());
        this.setAge(user.getAge());
        this.setMorphology(user.getMorphology());
        this.setCaloricPhase(user.getCaloricPhase());
        List<RoutineView> listRoutine = new ArrayList<>();
        Set<Routine> repeatedRoutines = new HashSet<>();
        if(routines != null){
            for(Routine routine: routines){
                if(!repeatedRoutines.contains(routine)){
                    repeatedRoutines.add(routine);
                    listRoutine.add(new RoutineView(routine));
                }
            }
        }
        this.setListRoutine(listRoutine);
        List<NutritionView> listNutrition = new ArrayList<>();
        Set<Nutrition> repeatedNutritions = new HashSet<>();
        if(nutritions != null){
            for(Nutrition nutrition: nutritions){
                if(!repeatedNutritions.contains(nutrition)){
                    repeatedNutritions.add(nutrition);
                    listNutrition.add(new NutritionView(nutrition));
                }
            }
        }
        this.setListNutrition(listNutrition);
    }
}
