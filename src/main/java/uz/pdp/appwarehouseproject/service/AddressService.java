package uz.pdp.appwarehouseproject.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Address;
import uz.pdp.appwarehouseproject.entity.Category;
import uz.pdp.appwarehouseproject.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public Response getAllAddress() {

        List<Address> list = addressRepository.findAll();
        if (list.isEmpty())
            return new Response("Database is empty", false);

        return new Response("Database ", true, list );

    }



    public Response getOneAddress(Integer id) {

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty())
            return new Response(" uzr bu id dagi address yuq", false);
        return new Response("success", true, optionalAddress.get());
    }

    public Response updateAddress(Integer id, Address addr) {


        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty())
            return new Response("such address id was not found", false);
        Address address = optionalAddress.get();
        address.setName(addr.getName());
        address.setPostalCode(addr.getPostalCode());

        addressRepository.save(address);

        return new Response("address updated", true);

    }

    public Response addAddress(Address address) {

        Address address1 = new Address();
        address1.setName(address.getName());
        address1.setPostalCode(address.getPostalCode());
        addressRepository.save(address1);

        return new Response("address added", true);

    }

    public Response deleteAddress(Integer id) {

        if (addressRepository.existsById(id)) {
            addressRepository.delete(addressRepository.getById(id));
            return new Response("User deleted!", true);
        } else {
            return new Response("such usr id was not found! ", false);
        }

    }
}
