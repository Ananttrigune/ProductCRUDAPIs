package com.vipgroup.products.services;

import com.vipgroup.products.dataTransferObjects.ProductsDTO_FakeStore;
import com.vipgroup.products.exceptions.InvalidInputsException;
import com.vipgroup.products.exceptions.ProductNotFound;
import com.vipgroup.products.models.Product;
import com.vipgroup.products.projections.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("FAKESTORE")
public class ProductService_FakeStore implements ProductService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    String url = "https://fakestoreapi.com/products";

    //Call actual Fake Store API, convert fakestore product to our Product model and return
    @Override
    public Product getProductById(long id) throws ProductNotFound {
        Product product = (Product) this.redisTemplate.opsForHash().get("PRODUCTS", "product_" + id);
        if (product != null) {
            return product;
        }

        String uri = url + "/" + id;
        ProductsDTO_FakeStore productFakeStore = this.restTemplate.getForObject(uri, ProductsDTO_FakeStore.class);
        if (productFakeStore == null) {
            throw new ProductNotFound("Product with id: " + id + " does not found!");
        }
        product = convert_ProductDTOFakeStore_Products(productFakeStore);
        this.redisTemplate.opsForHash().put("PRODUCTS", "product_" + id, product);
        return product;
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
    public Page<Product> getProducts(int pageSize, int pageNumber) {
        return null;
    }

    @Override
    public Page<Product> getProducts(int pageSize, int pageNumber, String sortFieldAndOrderBy) {
        return null;
    }

    @Override
    public Page<Product> getProductsContent(int pageSize, int pageNumber, String sortFieldAndOrderBy) throws InvalidInputsException {
        return null;
    }

    @Override
    public Product updateProductById(long productId, String name, String description, String category, float price) throws ProductNotFound {
        return null;
    }

    @Override
    public void deleteProductById(long productId) throws ProductNotFound {
    }

    @Override
    public ProductInfo getProductInfoById(long productId) throws ProductNotFound {
        return null;
    }

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