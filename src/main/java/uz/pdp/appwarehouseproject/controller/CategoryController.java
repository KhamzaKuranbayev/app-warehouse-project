package uz.pdp.appwarehouseproject.controller;


import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseproject.dto.CategoryDTO;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Category;
import uz.pdp.appwarehouseproject.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //CREATE
  @PostMapping
    public Response addCategory(@RequestBody CategoryDTO categoryDTO) {
      return categoryService.addCategory(categoryDTO);
    }

    //READ
    @GetMapping
    public Response readAddress() {
       return categoryService.readAddress();
    }

    // Read One
    @GetMapping("/{id}")
    public Response getOne(@PathVariable Integer id) {
        return categoryService.readOneAddress(id);
    }


    //UPDATE
    @PutMapping("/{id}")
    public  Response updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
         return   categoryService.editUser(id, categoryDTO);
    }

    //DELETE

    @DeleteMapping
    public  Response delete(@PathVariable Integer id){

      return categoryService.delete(id);

    }


}
