package com.example.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "tbl_shopkeeper")
public class ShopKeeper{
    @Id
    private String shopKeeperId;
    private String shopKeeperName;
    private String shopKeeperEmail;
    private String shopKeeperPassword;
    private String shopKeeperCity;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
}
