package com.example.service;

import java.util.List;

import com.example.dto.ProductDto;
import com.example.entity.Product;

public interface ProductService {
    Product addProduct(ProductDto productDto);

    Product updateProduct(ProductDto productDto, String prodId);

    void deleteProduct(String id);

    Product getProduct(String id);

    List<Product> getAllProducts();

	List<ProductDto> getProductByShopKeeperId(String id);
}
