package com.budiluhur.catalog.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String id) {
        super("Produk dengan ID " + id + " tidak ditemukan");
    }
}
