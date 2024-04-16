package ssdd.practicaWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ssdd.practicaWeb.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
