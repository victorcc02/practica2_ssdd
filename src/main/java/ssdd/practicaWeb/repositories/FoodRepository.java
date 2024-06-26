package ssdd.practicaWeb.repositories;

import ssdd.practicaWeb.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long>{
    Optional<Food> findByName(String name);
}
