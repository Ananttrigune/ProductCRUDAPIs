package com.vipgroup.products.controllers;

import com.vipgroup.products.dataTransferObjects.CreateProductRequestDTO;
import com.vipgroup.products.exceptions.ProductNotFound;
import com.vipgroup.products.models.Product;
import com.vipgroup.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController {

    @Autowired
    @Qualifier("MYSQL_Impl")
    private ProductService productService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        ResponseEntity<List<Product>> response = new ResponseEntity<>(products, HttpStatusCode.valueOf(200));
        return response;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") long productId) throws ProductNotFound {
        if (productId < 0) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
        Product product = new Product();
        product = productService.getProductById(productId);
        ResponseEntity<Product> response = new ResponseEntity<Product>(product, HttpStatusCode.valueOf(200));
        return response;
    }

    /*@PostMapping("")
    public Product createProduct(@RequestBody CreateProductRequestDTO requestDTO){
        System.out.println(requestDTO);
        return productService.createProduct(requestDTO.getName(), requestDTO.getDescription(), requestDTO.getCategory(), requestDTO.getPrice());
    }*/

    @PostMapping("")
    public Product createProduct(@RequestBody CreateProductRequestDTO requestDTO) {
        System.out.println(requestDTO);
        return productService.createProduct(requestDTO.getName(), requestDTO.getDescription(), requestDTO.getCategory(), requestDTO.getPrice());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProductById(@PathVariable("productId") long productId, @RequestBody CreateProductRequestDTO requestDTO) throws ProductNotFound {
        if (productId < 0) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
        Product product = productService.updateProductById(productId, requestDTO.getName(), requestDTO.getDescription(), requestDTO.getCategory(), requestDTO.getPrice());
        ResponseEntity<Product> response = new ResponseEntity<Product>(product, HttpStatusCode.valueOf(200));
        return response;
    }

   /* @GetMapping("/limit")
    public void getProductsWithLimits(@RequestParam int limit) {

    }*/

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("productId") long productId) throws ProductNotFound {
        if (productId < 0) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFound productNotFound) {
        return new ResponseEntity<>(productNotFound.getMessage(), HttpStatusCode.valueOf(404));
    }

}