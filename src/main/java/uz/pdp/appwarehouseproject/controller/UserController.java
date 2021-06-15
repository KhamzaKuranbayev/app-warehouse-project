package uz.pdp.appwarehouseproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.dto.UserDTO;
import uz.pdp.appwarehouseproject.entity.User;
import uz.pdp.appwarehouseproject.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/postalCode")
    public Response getAllUsersByPostalCode(@RequestParam(name = "pochtaIndex") String index) {
        Response response = userService.getAllUsersByPostalCode(index);
        return response;
    }

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping
    public Response addUser(@RequestBody UserDTO userDTO) {

        return userService.addUser(userDTO);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public Response editUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }


    @PreAuthorize(value = "hasAuthority('GET_ALL_USER')")
    @GetMapping
    public Response getAllUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        return userService.getAllUser();
    }

    @PreAuthorize(value = "hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @PreAuthorize(value = "hasAuthority('GET_USER')")
    @GetMapping("/{id}")
    public Response getOneUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

}
