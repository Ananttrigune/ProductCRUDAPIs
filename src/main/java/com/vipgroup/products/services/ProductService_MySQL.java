package com.vipgroup.products.services;

import com.vipgroup.products.exceptions.ProductNotFound;
import com.vipgroup.products.models.Product;
import com.vipgroup.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MYSQL_Impl")
public class ProductService_MySQL implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(long productId) throws ProductNotFound {
        return productRepository.findById(productId);
    }

    @Override
    public Product createProduct(String name, String description, String category, float price) {
        // Check for Duplicate ProductName
        /* SELECT * FROM products WHERE name={name}
        If this return record then product already exists with given name
         */
        //Product p = productRepository.findByName(name);
        Product p = productRepository.findByNameAndCategory(name, category);
        if (p != null) {
            return p;
        }
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setPrice(price);
        product = productRepository.save(product);
        System.out.println("Product created with Id: " + product.getId());
        return product;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product updateProductById(long productId, String name, String description, String category, float price) throws ProductNotFound {
        Product p = productRepository.findById(productId);
        if (p != null) {
            p.setName(name);
            p.setDescription(description);
            p.setCategory(category);
            p.setPrice(price);
            p = productRepository.save(p);
            System.out.println("Product details updated with Id: " + p.getId());
            return p;
        }
        return null;
    }

    @Override
    public void deleteProductById(long productId) throws ProductNotFound {
        productRepository.deleteById(productId);
    }

}