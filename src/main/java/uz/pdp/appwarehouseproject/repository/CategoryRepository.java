package uz.pdp.appwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouseproject.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository  extends JpaRepository<Category , Integer> {

    Optional<Category> findById(Integer id);  // abstract method

}
