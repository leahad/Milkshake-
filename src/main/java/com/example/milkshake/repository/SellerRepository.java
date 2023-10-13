package com.example.milkshake.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.milkshake.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    
    Optional<Seller> findSellerById(Long id);

    List<Seller> findSellersByNameContaining(String name);
}
