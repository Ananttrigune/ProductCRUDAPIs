package com.vipgroup.products.services;

import com.vipgroup.products.dataTransferObjects.ProductsDTO_FakeStore;
import com.vipgroup.products.exceptions.ProductNotFound;
import com.vipgroup.products.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("FAKESTORE")
public class ProductService_FakeStore implements ProductService {
    String url = "https://fakestoreapi.com/products";

    //Call actual Fake Store API, convert fakestore product to our Product model and return
    @Override
    public Product getProductById(long productId) throws ProductNotFound {
        String uri = url + "/" + productId;
        RestTemplate restTemplate = new RestTemplate();
        ProductsDTO_FakeStore productFakeStore = restTemplate.getForObject(uri, ProductsDTO_FakeStore.class);
        if (productFakeStore == null) {
            throw new ProductNotFound("Product with id: " + productId + " does not found!");
        }
        return convert_ProductDTOFakeStore_Products(productFakeStore);
    }

    @Override
    public Product createProduct(String name, String description, String category, float price) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product updateProductById(long productId, String name, String description, String category, float price) throws ProductNotFound {
        return null;
    }

    @Override
    public void deleteProductById(long productId) throws ProductNotFound {

    }

    /*public List<Products> getProductsWithLimit(int limitCount) {
        String uri = url + "?" + limitCount;
        List<ProductsDTO_FakeStore> productsFakeStore =new ArrayList<>();
        List<Products> products = new ArrayList<Products>();
        //Get the response and store into productsFakeStore
        for (ProductsDTO_FakeStore productFakeStore : productsFakeStore) {
            Products product = convert_ProductDTOFakeStore_Products(productFakeStore);
            products.add(product);
        }
        return products;
    }*/

    /*public List<Products> getProductsAll() {
        String uri = url + "/";
        List<ProductsDTO_FakeStore> productsFakeStore =new ArrayList<>();
        List<Products> products = new ArrayList<Products>();
        //Get the response and store into productsFakeStore
        for (ProductsDTO_FakeStore productFakeStore : productsFakeStore) {
            Products product = convert_ProductDTOFakeStore_Products(productFakeStore);
            products.add(product);
        }
        return products;
    }*/

    public Product convert_ProductDTOFakeStore_Products(ProductsDTO_FakeStore productDTO_FakeStore) {
        Product product = new Product();
        product.setId(productDTO_FakeStore.getId());
        product.setName(productDTO_FakeStore.getTitle());
        product.setDescription(productDTO_FakeStore.getDescription());
        product.setCategory(productDTO_FakeStore.getCategory());
        product.setPrice(productDTO_FakeStore.getPrice());
        return product;
    }

}