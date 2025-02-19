package com.example.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.dto.ProductDto;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Product addProduct(ProductDto productDto) {
        
       Product product = modelMapper.map(productDto, Product.class);
       product.setProdId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDto productDto, String prodId) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }

    @Override
    public Product getProduct(String id) {
    	return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found with "+id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

	@Override
	public List<ProductDto> getProductByShopKeeperId(String id) {
		List<Product> allByShopkeeperId = productRepository.findAllByShopkeeperId(id);
		List<ProductDto> dtos=modelMapper.map(allByShopkeeperId, List.class);
		return dtos;
	}

}
