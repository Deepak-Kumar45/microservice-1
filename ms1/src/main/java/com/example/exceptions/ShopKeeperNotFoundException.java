package com.example.exceptions;

import org.springframework.stereotype.Component;

@Component
public class ShopKeeperNotFoundException extends RuntimeException {
    public ShopKeeperNotFoundException() {
        super();
    }

    public ShopKeeperNotFoundException(String message) {
        super(message);
    }

}
