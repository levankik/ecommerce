package com.softgen.softlab.ecommercebackend.model.dao;

import com.softgen.softlab.ecommercebackend.model.Product;
import org.springframework.data.repository.ListCrudRepository;


public interface ProductDAO extends ListCrudRepository<Product, Long> {
}
