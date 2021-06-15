package uz.pdp.appwarehouseproject.service;


import org.springframework.stereotype.Service;
import uz.pdp.appwarehouseproject.dto.ProductDTO;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Category;
import uz.pdp.appwarehouseproject.entity.Product;
import uz.pdp.appwarehouseproject.repository.CategoryRepository;
import uz.pdp.appwarehouseproject.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public Response addProduct(ProductDTO productDTO) {

        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (optionalCategory.isEmpty())
            return new Response("Category id was not found", false);
        Category category = optionalCategory.get();

        Product product = new Product();
        product.setCategory(category);
        product.setName(productDTO.getName());
        product.setDescription(product.getDescription());

        productRepository.save(product);
        return new Response("Product was created", true);
    }

    public Response read() {

        List<Product> list = productRepository.findAll();
        if (list.isEmpty())
            return new Response("Database is empty", false);

        return new Response("Saved to  Database ", true, list );

    }

    public Response readOne(Integer id) {

        Optional<Product> byId = productRepository.findById(id);
        Product product = byId.get();
        if (byId.isEmpty())
            return new Response("such category id was not found", false);

        return new Response(" category id is  found", true, product);

    }

    public Response update  (Integer productId, ProductDTO productDTO) {

        if (productRepository.existsById(productId)) {

            Optional<Product> byId = productRepository.findById(productId);

            if (byId.isPresent()){

                Product product1 = byId.get();

                Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());

                if (optionalCategory.isEmpty())
                    return new Response("Sorry! Such product was not found", false);

                Category category = optionalCategory.get();

                product1.setCategory(category);
                product1.setDescription(productDTO.getDescription());
                product1.setName(productDTO.getName());

                productRepository.save(product1);
                return new Response("operation well done!", true);

            }

        }

        return new Response("such product id was not found",false);

    }

    public Response delete(Integer id) {
        if (productRepository.existsById(id)) {

            productRepository.deleteById(id);
            return new Response("Product deleted", true);
        }

        return new Response( "such product id was not found",false);
    }
}
