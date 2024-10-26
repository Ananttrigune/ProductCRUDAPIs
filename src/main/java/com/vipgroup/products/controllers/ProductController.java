package com.vipgroup.products.controllers;

import com.vipgroup.products.dataTransferObjects.CreateProductRequestDTO;
import com.vipgroup.products.exceptions.ProductAlreadyExists;
import com.vipgroup.products.exceptions.ProductMandatoryFieldsMissing;
import com.vipgroup.products.exceptions.ProductNotFound;
import com.vipgroup.products.models.Product;
import com.vipgroup.products.projections.ProductInfo;
import com.vipgroup.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
        Product product = productService.getProductById(productId);
        ResponseEntity<Product> response = new ResponseEntity<Product>(product, HttpStatusCode.valueOf(200));
        return response;
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDTO requestDTO) throws ProductAlreadyExists, ProductMandatoryFieldsMissing {
        System.out.println(requestDTO);
        Product product = productService.createProduct(requestDTO.getName(), requestDTO.getDescription(), requestDTO.getCategory(), requestDTO.getPrice());
        ResponseEntity<Product> response = new ResponseEntity<Product>(product, HttpStatusCode.valueOf(201));
        return response;
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProductById(@PathVariable("productId") long productId, @RequestBody CreateProductRequestDTO requestDTO) throws ProductNotFound, ProductMandatoryFieldsMissing, ProductAlreadyExists {
        Product product = productService.updateProductById(productId, requestDTO.getName(), requestDTO.getDescription(), requestDTO.getCategory(), requestDTO.getPrice());
        ResponseEntity<Product> response = new ResponseEntity<Product>(product, HttpStatusCode.valueOf(200));
        return response;
    }

    @GetMapping("/Info/{productId}")
    public ResponseEntity<ProductInfo> getProductInfoById(@PathVariable("productId") long productId) throws ProductNotFound {
        ProductInfo productIno = productService.getProductInfoById(productId);
        ResponseEntity<ProductInfo> response = new ResponseEntity<ProductInfo>(productIno, HttpStatusCode.valueOf(200));
        return response;
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Product>> getProductsWithPagination(
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber) throws Exception {
        // Invalid parameter for pageSize and pageNumber --> Throwing Exception message
        if (pageSize < 1 || pageNumber < 0) {
            throw new Exception("Input valid parameters for pageSize and/or pageNumber. \nValid values as pageSize>0 and pageNumber>=0");
        }
        /*
        // Invalid parameter for pageSize and pageNumber --> Assign default values
        if (pageSize < 1) pageSize = 10;
        if (pageNumber < 0) pageNumber = 0;
        */

        Page<Product> products = productService.getProducts(pageSize, pageNumber);
        ResponseEntity<Page<Product>> response = new ResponseEntity<>(products, HttpStatusCode.valueOf(200));
        return response;
    }

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

    @ExceptionHandler(ProductMandatoryFieldsMissing.class)
    public ResponseEntity<String> handleProductMandatoryFieldsMissingException(ProductMandatoryFieldsMissing productMandatoryFieldsMissing) {
        return new ResponseEntity<>(productMandatoryFieldsMissing.getMessage(), HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(ProductAlreadyExists.class)
    public ResponseEntity<String> handleProductDuplicatesException(ProductAlreadyExists productAlreadyExists) {
        return new ResponseEntity<>(productAlreadyExists.getMessage(), HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInvalidPaginationCountersException(Exception errorMessage) {
        return new ResponseEntity<>(errorMessage.getMessage(), HttpStatusCode.valueOf(400));
    }

}