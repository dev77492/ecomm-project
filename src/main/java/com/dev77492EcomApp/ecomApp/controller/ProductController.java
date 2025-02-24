package com.dev77492EcomApp.ecomApp.controller;

import com.dev77492EcomApp.ecomApp.model.Product;
import com.dev77492EcomApp.ecomApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;
    @RequestMapping("/")
    public String greet(){
        return "Welcome to the dev77492 world!";
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = service.getAllProducts();
//        System.out.println("Returning products: " + products); // Add logging to verify data
        return new ResponseEntity<>(products, HttpStatus.OK);

    }
    @GetMapping("/products/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId){

        Product product = service.getProductById(prodId);
        if(product!= null)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
