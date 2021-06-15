package uz.pdp.appwarehouseproject.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouseproject.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findById(Integer id);  // abstract method

    boolean existsById(Integer id);
}
