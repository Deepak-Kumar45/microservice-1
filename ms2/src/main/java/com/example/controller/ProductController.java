package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductDto;
import com.example.service.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	

    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }
    
    @GetMapping("/product/shopkeeper/{id}")
    public ResponseEntity<?> getProductByShopkeeperId(@PathVariable("id") String id) {
    	List<ProductDto> dtos=productService.getProductByShopKeeperId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductByid(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(id));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProductByid(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted with id " + id);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.addProduct(dto));
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
    
    

}
