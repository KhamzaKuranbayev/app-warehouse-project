package uz.pdp.appwarehouseproject.controller;


import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseproject.dto.OperationDTO;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.OperationHistory;
import uz.pdp.appwarehouseproject.entity.Product;
import uz.pdp.appwarehouseproject.entity.User;
import uz.pdp.appwarehouseproject.service.OperationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operation")
public class OperationController {



final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

// CREATE
    @PostMapping
public Response create (@RequestBody OperationDTO operationDTO) {
        return operationService.create(operationDTO);
    }

    //READ
   @GetMapping
    public Response getAll() {
       return operationService.getALl();
    }

    // UPDATE
   @PutMapping("/{id}")
    public  Response update(@PathVariable(name = "id") Integer operationId, @RequestBody OperationDTO operationDTO) {
      return operationService.update(operationDTO, operationId);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public  Response delete(@PathVariable Integer id){
       return operationService.delete(id);
    }



}
