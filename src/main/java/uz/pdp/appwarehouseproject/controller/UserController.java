package uz.pdp.appwarehouseproject.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.dto.UserDTO;
import uz.pdp.appwarehouseproject.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Response addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/{id}")
    public Response editUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @GetMapping
    public Response getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public Response getOneUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

}
