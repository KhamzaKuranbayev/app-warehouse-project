package uz.pdp.appwarehouseproject.controller;

import org.springframework.web.bind.annotation.*;
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

@RestController
@RequestMapping("/api/users")
public class UserController {

    final UserRepository userRepository;
    final AddressRepository addressRepository;
    final RoleRepository roleRepository;

    public UserController(UserRepository userRepository,
                          AddressRepository addressRepository,
                          RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping
    public String addUser(@RequestBody UserDTO userDTO) {

        Optional<Address> optionalAddress = addressRepository.findById(userDTO.getAddressId());
        if (optionalAddress.isEmpty())
            return "Sorry! Such address id was not found!";

        if(userRepository.existsByEmail(userDTO.getEmail()))
            return "Such email already exists in system!";

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
        return "User added successfully!";
    }

    @PutMapping("/{id}")
    public String editUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty())
            return "Such user id was not found!";

        User user = optionalUser.get();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());

        userRepository.save(user);
        return "User updated!";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return "sdsd";
    }

    @GetMapping
    public List<User> getAllUser() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable Integer id) {
        return new User();
    }

}
