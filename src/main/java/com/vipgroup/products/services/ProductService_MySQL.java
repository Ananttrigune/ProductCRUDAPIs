package com.vipgroup.products.services;

import com.vipgroup.products.exceptions.ProductAlreadyExists;
import com.vipgroup.products.exceptions.ProductMandatoryFieldsMissing;
import com.vipgroup.products.exceptions.ProductNotFound;
import com.vipgroup.products.models.Product;
import com.vipgroup.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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