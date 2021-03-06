package uz.pdp.appwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouseproject.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    boolean existsById(Integer id);
}
