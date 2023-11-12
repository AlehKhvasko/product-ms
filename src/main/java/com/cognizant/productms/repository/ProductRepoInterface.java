package com.cognizant.productms.repository;

import com.cognizant.productms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepoInterface extends JpaRepository<Product, Long> {
}
