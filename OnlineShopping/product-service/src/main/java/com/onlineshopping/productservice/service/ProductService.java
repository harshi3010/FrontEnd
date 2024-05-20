package com.onlineshopping.productservice.service;

import com.onlineshopping.productservice.dto.ProductRequest;
import com.onlineshopping.productservice.dto.ProductResponse;
import com.onlineshopping.productservice.model.Product;
import com.onlineshopping.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();  //it will create a object of product variable

        productRepository.save(product);
        log.info("Product {} is saved",product.getId());

    }


    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

//        products.stream().map(product -> mapToProductResponse(product))
        return products.stream().map(this::mapToProductResponse).toList();
    }
    //this mapToProductResponse class helps to map the product model class to map with productResponse dto class

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

}