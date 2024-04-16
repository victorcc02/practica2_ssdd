package ssdd.practicaWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ssdd.practicaWeb.entities.GymUser;

public interface UserRepository extends JpaRepository<GymUser, Long> {
}
