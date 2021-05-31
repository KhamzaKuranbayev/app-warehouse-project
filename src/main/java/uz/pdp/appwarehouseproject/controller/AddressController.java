package uz.pdp.appwarehouseproject.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseproject.entity.Address;
import uz.pdp.appwarehouseproject.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @GetMapping
    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Address getOneAddress(@PathVariable Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty())
            return new Address();
        return optionalAddress.get();
    }

    @PutMapping("/{id}")
    public String updateAddress(@PathVariable Integer id,@RequestBody Address addr){

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty())
            return "such address id was not found";
        Address address = optionalAddress.get();
        address.setName(addr.getName());
        address.setPostalCode(addr.getPostalCode());
        
        return "address updated";
    }
    
    @PutMapping
    public String addAddress(@RequestBody Address address){
        Address address1 = new Address();
        address1.setName(address.getName());
        address1.setPostalCode(address.getPostalCode());
        addressRepository.save(address1);

        return "address added";
    }

    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable Integer id){
        addressRepository.deleteById(id);
        return "address deleted";
    }


    
    
    
}
