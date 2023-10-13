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

import com.example.milkshake.entity.Milkshake;
import com.example.milkshake.repository.MilkshakeRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MilkshakeController {

    private MilkshakeRepository milkshakeRepository;

    MilkshakeController(MilkshakeRepository milkshakeRepository){
        this.milkshakeRepository = milkshakeRepository;
    };

    @GetMapping("/milkshakes")
    public ResponseEntity<List<Milkshake>> index() {
        List<Milkshake> milkshakes = milkshakeRepository.findAll();
        if (!milkshakes.isEmpty()) {
            return ResponseEntity.ok(milkshakes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/milkshake/{id}")
    public ResponseEntity<Optional<Milkshake>> show(@PathVariable Long id) {
        Optional<Milkshake> milkshake = milkshakeRepository.findMilkshakeById(id);
        if (milkshake.isPresent()) {
            return ResponseEntity.ok(milkshake);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/milkshakes/search")
    public ResponseEntity<List<Milkshake>> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        List<Milkshake> milkshakes = milkshakeRepository.findMilkshakesByNameContaining(searchTerm);
        if (!milkshakes.isEmpty()) {
            return ResponseEntity.ok(milkshakes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/milkshakes")
    public ResponseEntity<Milkshake> create(@RequestBody Milkshake milkshake){
        Milkshake savedMilkshake = milkshakeRepository.save(milkshake);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMilkshake);
    }

    @PutMapping("/milkshake/{id}")
    public ResponseEntity<Milkshake>update(@PathVariable long id, @RequestBody Milkshake milkshake){
        Milkshake milkshakeToUpdate = milkshakeRepository.findById(id).get();
        milkshakeToUpdate.setName(milkshake.getName());
        milkshakeToUpdate.setQuantity(milkshake.getQuantity());
        milkshakeToUpdate.setFlavour(milkshake.getFlavour());
        Milkshake updatedMilkshake = milkshakeRepository.save(milkshakeToUpdate);
        return ResponseEntity.ok(updatedMilkshake);
    }

    @DeleteMapping("milkshake/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        try {
            milkshakeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
}
