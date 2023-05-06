package com.softgen.softlab.ecommercebackend.api.controller.product;

import com.softgen.softlab.ecommercebackend.model.Product;
import com.softgen.softlab.ecommercebackend.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final  ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

}
