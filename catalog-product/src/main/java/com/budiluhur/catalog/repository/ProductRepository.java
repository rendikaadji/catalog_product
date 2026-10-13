package com.budiluhur.catalog.repository;

import com.budiluhur.catalog.exception.ProductNotFoundException;
import com.budiluhur.catalog.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(String id) throws ProductNotFoundException {
        for (Product p : products) {
            if (p.getId().equals(id)) return p;
        }
        throw new ProductNotFoundException(id);
    }
}
