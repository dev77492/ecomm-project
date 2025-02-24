package com.dev77492EcomApp.ecomApp.service;

import com.dev77492EcomApp.ecomApp.model.Product;
import com.dev77492EcomApp.ecomApp.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts(){
        List<Product> products = repo.findAll();
//        System.out.println("Fetched products: " + products); // Add logging to verify data
        return products;

    }

    public Product getProductById(int prodId) {
        return repo.findById(prodId).orElse(null);
    }
}
