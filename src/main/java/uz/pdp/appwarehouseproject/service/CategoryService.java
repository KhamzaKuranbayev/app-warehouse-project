package uz.pdp.appwarehouseproject.service;


import org.springframework.stereotype.Service;
import uz.pdp.appwarehouseproject.dto.CategoryDTO;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Category;
import uz.pdp.appwarehouseproject.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Response addCategory(CategoryDTO categoryDTO) {

        Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getParentId());
        if (optionalCategory.isEmpty())
            return new Response("sorry! such parentId was not found", false);

        Category category1 = new Category();
        category1.setName(categoryDTO.getName());
        category1.setDescription(categoryDTO.getDescription());

        Category category = optionalCategory.get();
        category1.setParent(category);


        categoryRepository.save(category1);
        return new Response("Sucessfully saved", true);


    }

    public Response editUser(Integer id, CategoryDTO categoryDTO) {

        if (categoryRepository.existsById(id)) {
            Optional<Category> categoryRepositoryById = categoryRepository.findById(id);

            if (categoryRepositoryById.isPresent()) {
                Category category1 = categoryRepositoryById.get();

                category1.setDescription(categoryDTO.getDescription());
                category1.setName(categoryDTO.getName());
                category1.setParent(category1);


                categoryRepository.save(category1);
                return new Response("Operation well done!", true);
            }

        }

        return new Response("Such Category was not found",false);


    }

    public Response delete(Integer id) {
        if (categoryRepository.existsById(id)) {

            categoryRepository.deleteById(id);
            return new Response("Address deleted ",true);

        }
        return new Response("Such Address not found", false);
    }

    public Response readAddress() {

        List<Category> list = categoryRepository.findAll();
        if (list.isEmpty())
            return new Response("Database is empty", false);

        return new Response("Database ", true, list );
    }

    public Response readOneAddress(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            return new Response("such category id was not found", false);

        return new Response(" category id is  found", true, optionalCategory.get());
    }
}
