package uz.pdp.appwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouseproject.entity.Address;
import uz.pdp.appwarehouseproject.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
