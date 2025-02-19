package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.ShopKeeper;

@Repository
public interface ShopKeeperRepository extends JpaRepository<ShopKeeper, String> {
    Optional<ShopKeeper> findByShopKeeperEmail(String shopKeeperMail);
}
