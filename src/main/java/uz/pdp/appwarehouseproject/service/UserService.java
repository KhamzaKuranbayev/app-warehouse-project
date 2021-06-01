package uz.pdp.appwarehouseproject.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.dto.UserDTO;
import uz.pdp.appwarehouseproject.entity.Address;
import uz.pdp.appwarehouseproject.entity.Role;
import uz.pdp.appwarehouseproject.entity.User;
import uz.pdp.appwarehouseproject.repository.AddressRepository;
import uz.pdp.appwarehouseproject.repository.RoleRepository;
import uz.pdp.appwarehouseproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final AddressRepository addressRepository;
    final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       AddressRepository addressRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.roleRepository = roleRepository;
    }

    public Response addUser(UserDTO userDTO) {
        Optional<Address> optionalAddress = addressRepository.findById(userDTO.getAddressId());
        if (optionalAddress.isEmpty())
            return new Response("Sorry! Such address id was not found!", false);

        if (userRepository.existsByEmail(userDTO.getEmail()))
            return new Response("Such email already exists in system!", false);

        User user = new User();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setCreatedAt(userDTO.getCreatedAt());

        Address address = optionalAddress.get();
        user.setAddress(address);

        List<Role> roleList = new ArrayList<>();

        List<Integer> roleIdList = userDTO.getRoleIdList();
        for (Integer roleId : roleIdList) {
            Optional<Role> optionalRole = roleRepository.findById(roleId);
            optionalRole.ifPresent(roleList::add);      // method reference - methodga ssilka
        }

        user.setRoles(roleList);
        userRepository.save(user);
        return new Response("User added successfully!", true);
    }

    public Response editUser(Integer id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new Response("Such user id was not found!", false);

        User user = optionalUser.get();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());

        userRepository.save(user);
        return new Response("User updated!", true);
    }

    public Response deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.delete(userRepository.getById(id));
            return new Response("User deleted!", true);
        } else {
            return new Response("such usr id was not found! ", false);
        }
    }

    public Response getAllUser() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty())
            return new Response("The database empty!", false);

        return new Response("Success!", true, userList);
    }

    public Response getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new Response("Such user id was not found!", false);
        return new Response("Success!", true, optionalUser.get());
    }

    public Response getAllUsersByPostalCode(String postalCode) {
        //List<User> userList = userRepository.getKetmonsByPochtaKodi(postalCode);
        //List<User> userList = userRepository.getKetmonsByPochtaKodiNative(postalCode);
        List<User> userList = userRepository.findAllByAddress_PostalCode(postalCode);

        if (userList.isEmpty())
            return new Response("Not Success!", false);

        return new Response("Success!", true, userList);
    }
}
