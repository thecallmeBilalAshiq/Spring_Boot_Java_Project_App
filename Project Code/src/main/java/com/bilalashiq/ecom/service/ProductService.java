package com.bilalashiq.ecom.service;

import com.bilalashiq.ecom.model.Product;
import com.bilalashiq.ecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductRepo repo;


    public List<Product> getAllProducts() {

        return repo.findAll();
    }


    public Product getProductByID(int id) {
        return repo.findById(id).orElse(null);
    }


    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());

        return repo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {

        product.setImageDate(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());

        return repo.save(product);
    }

    public void deleteProduct(int id)
    {
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword)
    {
        return repo.searchProducts(keyword);
    }
}