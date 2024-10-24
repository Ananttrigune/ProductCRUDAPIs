package com.vipgroup.products.repositories;

import com.vipgroup.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //Find Product by given name
    Product findByName(String name);

    //Find Product by given name and category
    Product findByNameAndCategory(String name, String category);

    //Find all Products
    List<Product> findAll();

    //Find Product by given Id
    Product findById(long id);

    //Find all Products by category
    List<Product> findAlByCategory(String category);

    //Delete Product by given Id
    void deleteById(long id);
}
