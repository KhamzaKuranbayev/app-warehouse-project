package uz.pdp.appwarehouseproject.service;


import org.springframework.stereotype.Service;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Role;
import uz.pdp.appwarehouseproject.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    // Create
    public Response addRole(Role role) {

        Role role1 = new Role();
        role1.setDescription(role.getDescription());
        role1.setRoleName(role.getRoleName());

        roleRepository.save(role1);
        return  new Response(" Saved", true);


    }


    public Response getRole() {

        List<Role> list = roleRepository.findAll();
        if (list.isEmpty())
            return new Response(" Database hasn't things", false);

        return new Response("Good job bro", true, list);

    }

    public Response update(Integer roleId, Role role) {

        if (roleRepository.existsById(roleId)){

            Optional<Role> byId = roleRepository.findById(roleId);

            if (byId.isPresent()) {

                Role role1 = byId.get();
                role1.setRoleName(role.getRoleName());
                role1.setDescription(role.getDescription());

                roleRepository.save(role1);
                return new Response("Saved", true);

            }

        }

        return new Response("Such role id was not found", false);

    }


    public Response delete(Integer id) {

        if (roleRepository.existsById(id)) {

            roleRepository.deleteById(id);

            return  new Response(" Role deleted", true);

        }

        return  new Response("Such role id was not found", false);

    }
}
