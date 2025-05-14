package com.thanhty.models;

import java.util.ArrayList;

public class ListProduct {
    private ArrayList<Product> products;

    public ListProduct() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void gen_dataset() {
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("Smart LED TV 55-inch");
        p1.setPrice(799.99);
        products.add(p1);

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("Leather Jacket - Black");
        p2.setPrice(149.99);
        products.add(p2);

        Product p3 = new Product();
        p3.setId(3);
        p3.setName("Sci-Fi Novel Collection");
        p3.setPrice(29.99);
        products.add(p3);

        Product p4 = new Product();
        p4.setId(4);
        p4.setName("Smart Blender Pro");
        p4.setPrice(89.99);
        products.add(p4);

        Product p5 = new Product();
        p5.setId(5);
        p5.setName("Professional Hiking Backpack");
        p5.setPrice(69.99);
        products.add(p5);

        Product p6 = new Product();
        p6.setId(6);
        p6.setName("Organic Skincare Set");
        p6.setPrice(49.99);
        products.add(p6);

        Product p7 = new Product();
        p7.setId(7);
        p7.setName("Wireless Noise-Canceling Headphones");
        p7.setPrice(199.99);
        products.add(p7);

        Product p8 = new Product();
        p8.setId(8);
        p8.setName("Designer Sunglasses");
        p8.setPrice(99.99);
        products.add(p8);
    }
}