package uz.pdp.appwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouseproject.entity.OperationHistory;

@Repository
public interface OperationsRepository extends JpaRepository<OperationHistory , Integer> {


}
