package ssdd.practicaWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ssdd.practicaWeb.entities.Routine;
public interface RoutineRepository extends JpaRepository<Routine, Long> {
}
