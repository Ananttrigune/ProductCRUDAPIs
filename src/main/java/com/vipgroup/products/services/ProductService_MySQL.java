package com.vipgroup.products.services;

import com.vipgroup.products.exceptions.*;
import com.vipgroup.products.models.Product;
import com.vipgroup.products.projections.ProductInfo;
import com.vipgroup.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("MYSQL_Impl")
public class ProductService_MySQL implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(long productId) throws ProductNotFound {
        return getProductIfExists(productId);
    }

    @Override
    public Product createProduct(String name, String description, String category, float price) throws ProductAlreadyExists, ProductMandatoryFieldsMissing {
        if (checkProductForMandatoryFields(name, category)) {
            if (checkProductForDuplicateOnCreate(name, category)) {
                return null;
            } else {
                Product product = new Product();
                product.setName(name);
                product.setDescription(description);
                product.setCategory(category);
                product.setPrice(price);
                product = productRepository.save(product);
                System.out.println("Product created with Id: " + product.getId());
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Page<Product> getProducts(int pageSize, int pageNumber) {
        Page<Product> products = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return products;
    }

    @Override
    public Page<Product> getProducts(int pageSize, int pageNumber, String sortField) throws InvalidInputsException {
        String[] s = sortField.split(",");
        List<String> fields = new ArrayList<>();
        List<String> orders = new ArrayList<>();
        boolean fieldAdded, orderAdded;
        if (s[0].equalsIgnoreCase("name") ||
                s[0].equalsIgnoreCase("description") ||
                s[0].equalsIgnoreCase("category") ||
                s[0].equalsIgnoreCase("price") ||
                s[0].equalsIgnoreCase("id")) {
            fields.add(s[0]);
            fieldAdded = true;
            orderAdded = false;
        } else {
            throw new InvalidInputsException(s[0] + ErrorMesages.INVALID_SORTING_FIELDNAME);
        }
        for (int i = 1; i < s.length; i++) {
            String c = s[i];
            if (c.equalsIgnoreCase("name") ||
                    c.equalsIgnoreCase("description") ||
                    c.equalsIgnoreCase("category") ||
                    c.equalsIgnoreCase("price") ||
                    c.equalsIgnoreCase("id")) {
                if (orderAdded) {
                    fields.add(c);
                    fieldAdded = true;
                    orderAdded = false;
                } else {
                    orders.add("ASC");
                    fieldAdded = false;
                    orderAdded = true;
                    fields.add(c);
                    fieldAdded = true;
                    orderAdded = false;
                }
            } else if (c.equalsIgnoreCase("ASC") || c.equalsIgnoreCase("DESC")) {
                if (orderAdded) {
                    throw new InvalidInputsException(s[0] + ErrorMesages.INVALID_SORTING_FIELDNAME);
                } else {
                    orders.add(c);
                    fieldAdded = false;
                    orderAdded = true;
                }
            } else {
                throw new InvalidInputsException(s[0] + ErrorMesages.INVALID_SORTING_FIELDNAME);
            }
        }
        List<Sort.Order> orderList = new ArrayList<>();
        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            String direction = orders.get(i);
            orderList.add("ASC".equalsIgnoreCase(direction) ? Sort.Order.asc(field) : Sort.Order.desc(field));
        }
        Sort sort = Sort.by(orderList);
        Page<Product> products = productRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
        //List<Product> listOfProducts = productRepository.findAll(PageRequest.of(pageNumber, pageSize, sort)).getContent();
        return products;
    }

    @Override
    public Page<Product> getProductsContent(int pageSize, int pageNumber, String sortField) throws InvalidInputsException {
        String[] s = sortField.split(",");
        List<String> fields = new ArrayList<>();
        List<String> orders = new ArrayList<>();
        boolean fieldAdded, orderAdded;
        if (s[0].equalsIgnoreCase("name") ||
                s[0].equalsIgnoreCase("description") ||
                s[0].equalsIgnoreCase("category") ||
                s[0].equalsIgnoreCase("price") ||
                s[0].equalsIgnoreCase("id")) {
            fields.add(s[0]);
            fieldAdded = true;
            orderAdded = false;
        } else {
            throw new InvalidInputsException(s[0] + ErrorMesages.INVALID_SORTING_FIELDNAME);
        }
        for (int i = 1; i < s.length; i++) {
            String c = s[i];
            if (c.equalsIgnoreCase("name") ||
                    c.equalsIgnoreCase("description") ||
                    c.equalsIgnoreCase("category") ||
                    c.equalsIgnoreCase("price") ||
                    c.equalsIgnoreCase("id")) {
                if (orderAdded) {
                    fields.add(c);
                    fieldAdded = true;
                    orderAdded = false;
                } else {
                    orders.add("ASC");
                    fieldAdded = false;
                    orderAdded = true;
                    fields.add(c);
                    fieldAdded = true;
                    orderAdded = false;
                }
            } else if (c.equalsIgnoreCase("ASC") || c.equalsIgnoreCase("DESC")) {
                if (orderAdded) {
                    throw new InvalidInputsException(s[i] + ErrorMesages.INVALID_SORTING_FIELDNAME);
                } else {
                    orders.add(c);
                    fieldAdded = false;
                    orderAdded = true;
                }
            } else {
                throw new InvalidInputsException(s[i] + ErrorMesages.INVALID_SORTING_FIELDNAME);
            }
        }
        List<Sort.Order> orderList = new ArrayList<>();
        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            String direction = orders.get(i);
            orderList.add("ASC".equalsIgnoreCase(direction) ? Sort.Order.asc(field) : Sort.Order.desc(field));
        }
        Sort sort = Sort.by(orderList);
        Page<Product> products = productRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
        return products;
        //List<Product> productContents = products.getContent();
        //return productContents;
    }

    @Override
    public Product updateProductById(long productId, String name, String description, String category, float price) throws
            ProductNotFound, ProductMandatoryFieldsMissing, ProductAlreadyExists {
        Product product = getProductIfExists(productId);
        if (checkProductForMandatoryFields(name, category)) {
            if (!checkProductForDuplicateOnUpdate(name, category, product)) {
                //Set all values
                product.setName(name);
                product.setDescription(description);
                product.setCategory(category);
                product.setPrice(price);
                productRepository.save(product);
            }
        }
        System.out.println("Product details updated with Id: " + product.getId());
        return product;
    }

    @Override
    public void deleteProductById(long productId) throws ProductNotFound {
        Product product = getProductIfExists(productId);
        productRepository.deleteById(productId);
        System.out.println("Product details deleted with Id: " + product.getId());
    }

    @Override
    public ProductInfo getProductInfoById(long productId) throws ProductNotFound {
        ProductInfo productInfo = productRepository.getProductInfoById(productId);
        return productInfo;
    }

    public Product getProductIfExists(long productId) throws ProductNotFound {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFound("Product with id: " + productId + " does not found!");
        }
        return product.get();
    }

    public boolean checkProductForDuplicateOnCreate(String name, String category) throws ProductAlreadyExists {
        // Check for Duplicate ProductName: SELECT * FROM products WHERE name={name} AND category={category}
        Product product = productRepository.findByNameAndCategory(name, category);
        if (product != null) {
            throw new ProductAlreadyExists("Product already exists with given name and category combination. " +
                    "\nProduct Details are as follows: " +
                    "\n\t id: " + product.getId() +
                    "\n\t name: " + product.getName() +
                    "\n\t cateory: " + product.getCategory() +
                    "\n\t descrition: " + product.getDescription());
            //throw new ProductAlreadyExists("Product already exists");
        } else {
            return false;
        }
    }

    public boolean checkProductForDuplicateOnUpdate(String name, String category, Product p) throws ProductAlreadyExists {
        // Check for Duplicate ProductName: SELECT * FROM products WHERE name={name} AND category={category}
        Product product = productRepository.findByNameAndCategoryAndIdNot(name, category, p.getId());
        if (product != null) {
            String message = null;
            message = "\nProduct Details are as follows: " +
                    "\n\t id: " + product.getId() +
                    "\n\t name: " + product.getName() +
                    "\n\t cateory: " + product.getCategory() +
                    "\n\t descrition: " + product.getDescription();
            throw new ProductAlreadyExists("Product already exists with given name and category combination. " +
                    message);
        } else {
            return false;
        }
    }

    public boolean checkProductForMandatoryFields(String name, String category) throws ProductMandatoryFieldsMissing {
        if (name.isEmpty() || category.isEmpty()) {
            throw new ProductMandatoryFieldsMissing("Product mandatory fields name and/or category are missing");
        } else {
            return true;
        }
    }

    public boolean checkProductForMandatoryFields(String nameORcategory) throws ProductMandatoryFieldsMissing {
        if (nameORcategory.isEmpty()) {
            throw new ProductMandatoryFieldsMissing("Product mandatory fields name and/or category are missing");
        } else {
            return true;
        }
    }

}