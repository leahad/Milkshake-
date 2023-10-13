package com.example.milkshake;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.milkshake.entity.Milkshake;
import com.example.milkshake.entity.Seller;
import com.example.milkshake.repository.MilkshakeRepository;
import com.example.milkshake.repository.SellerRepository;

@SpringBootApplication
public class MilkshakeApplication {

	private MilkshakeRepository milkshakeRepository;
	private SellerRepository sellerRepository;

    MilkshakeApplication(MilkshakeRepository milkshakeRepository, SellerRepository sellerRepository){
        this.milkshakeRepository = milkshakeRepository;
		this.sellerRepository = sellerRepository;
    };

	public static void main(String[] args) {
		SpringApplication.run(MilkshakeApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {	
		return (String[] args) -> {
			Milkshake milkshake1 = new Milkshake("Milkshake fraise","fraise", 30);
			milkshakeRepository.save(milkshake1);

			Milkshake milkshake2 = new Milkshake("Milkshake vanille","vanille", 20);
			milkshakeRepository.save(milkshake2);

			Milkshake milkshake3 = new Milkshake("Milkshake choco","chocolat", 15);
			milkshakeRepository.save(milkshake3 );

			Seller seller1 = new Seller("Erika", 27);
			sellerRepository.save(seller1);

			Seller seller2 = new Seller("Léa", 38);
			sellerRepository.save(seller2);
		};
	}

}
