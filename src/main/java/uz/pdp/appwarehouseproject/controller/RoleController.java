package uz.pdp.appwarehouseproject.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseproject.entity.Role;
import uz.pdp.appwarehouseproject.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Role getOneRole(@PathVariable Integer id) {
        Optional<Role> byId = roleRepository.findById(id);
        if (byId.isEmpty())
            return null;
        return byId.get();
    }

    @PostMapping
    public String addRole(@RequestBody Role role) {
        roleRepository.save(role);
        return "Role added ";
    }

    @PutMapping("/{id}")
    public String updateRole(@PathVariable Integer id,@RequestBody Role role){
        Optional<Role> role1 = roleRepository.findById(id);
        if (role1.isEmpty())
            return "such role id was not found";
        Role role2 = role1.get();
        role2.setRoleName(role.getRoleName());
        role2.setDescription(role.getDescription());

        return "Role updated";
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Integer id){
        Optional<Role> role1 = roleRepository.findById(id);
        if (role1.isEmpty())
            return "such role id was not found";
        roleRepository.deleteById(id);
        return "role deleted";
    }

}
