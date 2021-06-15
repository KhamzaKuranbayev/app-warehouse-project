package uz.pdp.appwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouseproject.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {


    boolean existsById(Integer integer);

}
