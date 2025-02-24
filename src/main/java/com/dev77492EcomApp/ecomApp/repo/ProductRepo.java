package com.dev77492EcomApp.ecomApp.repo;

import com.dev77492EcomApp.ecomApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
