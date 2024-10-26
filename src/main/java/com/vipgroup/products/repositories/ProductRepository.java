package com.vipgroup.products.repositories;

import com.vipgroup.products.models.Product;
import com.vipgroup.products.projections.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //Find Product by given name
    Product findByName(String name);

    //Find Product by given name and category
    Product findByNameAndCategory(String name, String category);

    //Find Product by given name and category
    Product findByNameAndCategoryAndIdNot(String name, String category, long id);

    //Find all Products
    List<Product> findAll();

    //Fina all Products suporting Pagination
     Page<Product> findAll(Pageable pageable);

    //Find Product by given Id
    /*Product findById(long id);*/
    Optional<Product> findById(long id);

    //Find all Products by category
    List<Product> findAlByCategory(String category);

    //Delete Product by given Id
    void deleteById(long id);

    @Query(nativeQuery = true, value = "select p.id, p.name, p.description from product p where id=:id")
    ProductInfo getProductInfoById(long id);

}