package ra.apisecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.apisecurity.model.entity.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String user);
}
