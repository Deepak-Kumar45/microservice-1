package com.example.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {
	private String prodId;
    private String prodName;
    private String description;
    private Double price;
    private String category;
    private Date createdAt;
    private Date updatedAt;
}
