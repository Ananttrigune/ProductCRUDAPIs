package com.vipgroup.products.services;

import com.vipgroup.products.exceptions.ProductAlreadyExists;
import com.vipgroup.products.exceptions.ProductMandatoryFieldsMissing;
import com.vipgroup.products.exceptions.ProductNotFound;
import com.vipgroup.products.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(long productId) throws ProductNotFound;

    public Product createProduct(String name, String description, String category, float price) throws ProductAlreadyExists, ProductMandatoryFieldsMissing;

    public List<Product> getProducts();

    public Product updateProductById(long productId, String name, String description, String category, float price) throws ProductNotFound, ProductMandatoryFieldsMissing, ProductAlreadyExists;

    public void deleteProductById(long productId) throws ProductNotFound;
}