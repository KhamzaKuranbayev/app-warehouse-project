package uz.pdp.appwarehouseproject.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseproject.dto.CategoryDTO;
import uz.pdp.appwarehouseproject.entity.Category;
import uz.pdp.appwarehouseproject.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    final CategoryRepository categoryRepository;


    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category getOneCategory(@PathVariable Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty())
            return new Category();
        return category.get();
    }

    @PostMapping
    public String addCategory(@RequestBody CategoryDTO categoryDTO) {
        Optional<Category> category1 = categoryRepository.findById(categoryDTO.getParentId());
        if (category1.isEmpty())
            return "such category id was not found";

        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setParent(category1.get());

        categoryRepository.save(category);
        return "category added";

    }

    @PutMapping("/{id}")
    public String updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        Optional<Category> categoryById = categoryRepository.findById(id);
        if (categoryById.isEmpty())
            return "such category id was not found";
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getParentId());
        if (optionalCategory.isEmpty())
            return "such parentId was not found";

        Category category = categoryById.get();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setParent(optionalCategory.get());
        return "category updated";

    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Integer id){
        categoryRepository.deleteById(id);
        return "category deleted";
    }
}
