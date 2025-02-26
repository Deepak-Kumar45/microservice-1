package com.example.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "product-client",url = "localhost:8085", path = "/std-app/api")
@FeignClient(name = "product-client", url = "${std-app.url}")
public interface ProductClient {
	
	@GetMapping("/product/shopkeeper/{id}")
    public ResponseEntity<?> getProductByShopkeeperId(@PathVariable("id") String id);
}
