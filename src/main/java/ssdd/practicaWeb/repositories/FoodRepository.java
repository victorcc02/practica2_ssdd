package ssdd.practicaWeb.repositories;

import ssdd.practicaWeb.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long>{
}
