package com.dev77492EcomApp.ecomApp.controller;

import com.dev77492EcomApp.ecomApp.model.Product;
import com.dev77492EcomApp.ecomApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.InsetsUIResource;
import java.io.IOException;
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
    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId){

        Product product = service.getProductById(prodId);
        if(product!= null)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
        try{
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile) {
        Product product1 = null;
        try {
            product1 = service.updateProduct(id, product, imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to Update" , HttpStatus.BAD_REQUEST);
        }

        if(product1 != null){
            return new ResponseEntity<>("Updated" , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Failed to Update" , HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = service.getProductById(id);
        if(product !=null){
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted ", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        List<Product> products= service.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
