package uz.pdp.appwarehouseproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouseproject.entity.Address;

import java.util.List;

public interface AddressRepository  extends JpaRepository<Address, Integer > {

    // Addresslarni ichidan id buyicha tekwiraman va boolean qaytaraman. mavjud bulsa true yuq bulsa false qaytaraman

    boolean existsById(Integer id);




    List<Address> findAllByPostalCode(String postalCode);  // postalCode = 1

    Page<Address> findAllByPostalCode(String postalCode, Pageable pageable);        // postalCode = 1

}
