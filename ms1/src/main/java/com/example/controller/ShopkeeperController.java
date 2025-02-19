package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ShopKeeperDto;
import com.example.exceptions.ShopKeeperNotFoundException;
import com.example.service.ShopkeeperService;

@RestController
public class ShopkeeperController {

    private ShopkeeperService shopkeeperService; 
	
	public ShopkeeperController(ShopkeeperService shopkeeperService) {
		this.shopkeeperService=shopkeeperService;
	}
	

    @GetMapping("/shopkeeper")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(shopkeeperService.getAllShopKeeper());
    }
    
    @GetMapping("/shopkeeper-product/{id}")
    public ResponseEntity<?> getProductsOfShopkeeper(@PathVariable("id") String id){
    	ShopKeeperDto productsOfShopkeepr = shopkeeperService.getProductsOfShopkeepr(id);
    	return ResponseEntity.status(HttpStatus.OK).body(productsOfShopkeepr);
    }

    @GetMapping("/shopkeeper/{id}")
    public ResponseEntity<?> getProductByid(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(shopkeeperService.getShopKeeperById(id));
    }

    @DeleteMapping("/shopkeeper/{id}")
    public ResponseEntity<?> deleteProductByid(@PathVariable("id") String id) {
//        shopkeeperService.
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted with id " + id);
    }

    @PostMapping("/shopkeeper")
    public ResponseEntity<?> addProduct(@RequestBody ShopKeeperDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(shopkeeperService.addShopKeeper(dto));
    }
    
    @ExceptionHandler(ShopKeeperNotFoundException.class)
    public ResponseEntity<?> handleException(ShopKeeperNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
