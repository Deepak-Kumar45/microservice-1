package com.example.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ProductDto {
    private String prodId;
    private String prodName;
    private String description;
    private Double price;
    private String category;
    private Date createdAt;
    private Date updatedAt;
    private String shopkeeperId;
}
