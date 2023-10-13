package com.example.milkshake.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.milkshake.entity.Seller;
import com.example.milkshake.repository.SellerRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class SellerController {

    private SellerRepository sellerRepository;

    SellerController(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    };

    @GetMapping("/sellers")
    public ResponseEntity<List<Seller>> index() {
        List<Seller> sellers = sellerRepository.findAll();
        if (!sellers.isEmpty()) {
            return ResponseEntity.ok(sellers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/seller/{id}")
    public ResponseEntity<Optional<Seller>> show(@PathVariable Long id) {
        Optional<Seller> seller = sellerRepository.findSellerById(id);
        if (seller.isPresent()) {
            return ResponseEntity.ok(seller);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sellers/search")
    public ResponseEntity<List<Seller>> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        List<Seller> sellers = sellerRepository.findSellersByNameContaining(searchTerm);
        if (!sellers.isEmpty()) {
            return ResponseEntity.ok(sellers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sellers")
    public ResponseEntity<Seller> create(@RequestBody Seller seller){
        Seller savedSeller = sellerRepository.save(seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeller);
    }

    @PutMapping("/sellers/{id}")
    public ResponseEntity<Seller>update(@PathVariable long id, @RequestBody Seller seller){
        Seller sellerToUpdate = sellerRepository.findById(id).get();
        sellerToUpdate.setName(seller.getName());
        sellerToUpdate.setAge(seller.getAge());
        Seller updatedseller = sellerRepository.save(sellerToUpdate);
        return ResponseEntity.ok(updatedseller);
    }

    @DeleteMapping("seller/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        try {
            sellerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
}
