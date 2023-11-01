package com.example.milkshake.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.milkshake.entity.Milkshake;

public interface MilkshakeRepository extends JpaRepository<Milkshake, Long> {

    List<Milkshake> findMilkshakesByNameContaining(String name);

}
