package uz.pdp.appwarehouseproject.controller;


import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseproject.dto.ProductDTO;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Category;
import uz.pdp.appwarehouseproject.entity.Product;
import uz.pdp.appwarehouseproject.service.ProductService;

import java.util.List;
import java.util.Optional;


@RequestMapping(path = "/api/product")
@RestController
public class ProductController {

    //  GET, POST, PUT, DELETE,..     http://172.168.255.12/api/product

 final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //CREATE
   @PostMapping
    public Response addProduct(@RequestBody ProductDTO productDTO) {

    return  productService.addProduct(productDTO);
    }

    // READ
   @GetMapping
    public Response read() {

       return  productService.read();
    }

    // Read one
    @GetMapping("/{id}")
    public  Response readOne(@PathVariable Integer id){
        return productService.readOne(id);
    }


    //UPDATE
  @PutMapping("/{id}")
    public Response update(@PathVariable(name = "id") Integer productId, @RequestBody ProductDTO productDTO) {

      return   productService.update(productId, productDTO);

    }

    //DELETE

    @DeleteMapping("/{id}")
    public  Response delete(@PathVariable Integer id) {

        return productService.delete(id);
    }


}
