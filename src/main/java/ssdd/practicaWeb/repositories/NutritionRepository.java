package ssdd.practicaWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ssdd.practicaWeb.entities.Nutrition;

public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
}
