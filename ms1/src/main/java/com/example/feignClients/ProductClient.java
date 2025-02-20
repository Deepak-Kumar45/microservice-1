package com.example.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "product-client", url = "${std-app.url}")
public interface ProductClient {

    @GetMapping("/product/shopkeeper/{id}")
    public ResponseEntity<?> getProductByShopkeeperId(@PathVariable("id") String id);

}