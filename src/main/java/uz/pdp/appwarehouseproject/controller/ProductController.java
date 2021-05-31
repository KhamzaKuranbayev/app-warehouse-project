package uz.pdp.appwarehouseproject.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseproject.dto.ProductDTO;
import uz.pdp.appwarehouseproject.entity.Category;
import uz.pdp.appwarehouseproject.entity.Product;
import uz.pdp.appwarehouseproject.repository.CategoryRepository;
import uz.pdp.appwarehouseproject.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            return null;
        return optionalProduct.get();
    }

    @PostMapping
    public String addProduct(@RequestBody ProductDTO productDTO){
        Product product = new Product();
        Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
        if (category.isEmpty())
            return "such category id was not found";
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setCategory(category.get());

        productRepository.save(product);
        return "product added";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Integer id,@RequestBody ProductDTO productDTO){
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            return "such product id was not found";
        Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
        if (category.isEmpty())
            return "such category id was not found";

        Product product1 = product.get();
        product1.setName(productDTO.getName());
        product1.setDescription(productDTO.getDescription());
        product1.setCategory(category.get());

        return "product updated";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            return "such product id was not found";
        productRepository.delete(product.get());
        return "product deleted";

    }

}
