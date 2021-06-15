package uz.pdp.appwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouseproject.entity.OperationHistory;

public interface OperationRepository extends JpaRepository<OperationHistory, Integer> {


    boolean existsById(Integer id);
}
