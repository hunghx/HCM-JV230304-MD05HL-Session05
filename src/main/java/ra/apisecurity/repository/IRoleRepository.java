package ra.apisecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.apisecurity.model.entity.Role;
import ra.apisecurity.model.entity.RoleName;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
