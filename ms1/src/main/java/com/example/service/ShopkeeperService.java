package com.example.service;

import java.util.List;

import com.example.dto.ShopKeeperDto;
import com.example.entity.ShopKeeper;

public interface ShopkeeperService {

    ShopKeeper addShopKeeper(ShopKeeperDto dto);

    ShopKeeper getShopKeeperById(String id);

    ShopKeeper getShopKeeperByEmail(String email);

    List<ShopKeeper> getAllShopKeeper();

    ShopKeeperDto getProductsOfShopkeepr(String id);

}
