package uz.pdp.appwarehouseproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouseproject.entity.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findAllByPostalCode(String postalCode);  // postalCode = 1

    Page<Address> findAllByPostalCode(String postalCode, Pageable pageable);        // postalCode = 1

}
