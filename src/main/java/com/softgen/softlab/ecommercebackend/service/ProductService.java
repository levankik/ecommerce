package com.softgen.softlab.ecommercebackend.service;

import com.softgen.softlab.ecommercebackend.model.Product;
import com.softgen.softlab.ecommercebackend.model.dao.ProductDAO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;

    public List<Product> getProducts() {
        return productDAO.findAll();
    }
}
