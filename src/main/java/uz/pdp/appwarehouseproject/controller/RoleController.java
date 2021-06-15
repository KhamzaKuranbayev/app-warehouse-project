package uz.pdp.appwarehouseproject.controller;


import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Role;
import uz.pdp.appwarehouseproject.service.RoleService;

import java.util.List;
import java.util.Optional;
@RequestMapping ("/api/role/")
@RestController
public class RoleController {


    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    //CREATE
   @PostMapping
    public Response addRole(@PathVariable Role role) {
return  roleService.addRole(role);
            }

    //READ
   @GetMapping
    public Response  getRole() {
       return roleService.getRole();
    }


    //UPDATE
  @PutMapping("/{id}")
    public  Response  update(@PathVariable(name = "id") Integer roleId, @RequestBody Role role) {

    return     roleService.update(roleId, role);

    }

    //DELETE
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id) {

       return roleService.delete(id);

    }


}
