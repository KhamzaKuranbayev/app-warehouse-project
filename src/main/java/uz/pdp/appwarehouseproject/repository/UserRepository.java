package uz.pdp.appwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouseproject.entity.User;

import java.util.List;

@Repository
public interface  UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    // JPA Query
    List<User> findAllByAddress_PostalCode(String address_postalCode);      // alt+enter

    // JPQL 1-usul
    /*@Query("select u from User u where u.address.postalCode = :postalCode")
    List<User> getKetmonsByPochtaKodi(String postalCode);
    */

    //2-usul
    @Query("select u from User u where u.address.postalCode = ?1")
    List<User> getKetmonsByPochtaKodi(String postalCode);

    // Native Query
    @Query(value = "select * from users u join address a on u.address_id = a.id where a.postal_code = :pochtaIndex", nativeQuery = true)
    List<User> getKetmonsByPochtaKodiNative(String pochtaIndex);





}
