package com.vipgroup.products.services;

import com.vipgroup.products.exceptions.InvalidInputsException;
import com.vipgroup.products.exceptions.ProductAlreadyExists;
import com.vipgroup.products.exceptions.ProductMandatoryFieldsMissing;
import com.vipgroup.products.exceptions.ProductNotFound;
import com.vipgroup.products.models.Product;
import com.vipgroup.products.projections.ProductInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public Product getProductById(long productId) throws ProductNotFound;

    public Product createProduct(String name, String description, String category, float price) throws ProductAlreadyExists, ProductMandatoryFieldsMissing;

    public List<Product> getProducts();

    public Page<Product> getProducts(int pageSize, int pageNumber);

    public Page<Product> getProducts(int pageSize, int pageNumber, String sortFieldAndOrderBy) throws InvalidInputsException;

    public Page<Product> getProductsContent(int pageSize, int pageNumber, String sortFieldAndOrderBy) throws InvalidInputsException;

    public Product updateProductById(long productId, String name, String description, String category, float price) throws ProductNotFound, ProductMandatoryFieldsMissing, ProductAlreadyExists;

    public void deleteProductById(long productId) throws ProductNotFound;

    public ProductInfo getProductInfoById(long productId) throws ProductNotFound;
}