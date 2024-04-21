package ssdd.practicaWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ssdd.practicaWeb.entities.GymUser;
import ssdd.practicaWeb.entities.Nutrition;

import java.util.List;

public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
    List<Nutrition> findByGymUser(GymUser gymUser);
}
