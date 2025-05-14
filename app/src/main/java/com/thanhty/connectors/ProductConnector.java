package com.thanhty.connectors;

import com.thanhty.models.ListProduct;
import com.thanhty.models.Product;

public class ProductConnector {
    public Product getProductById(int id) {
        ListProduct lp = new ListProduct();
        lp.gen_dataset();
        for (Product prod : lp.getProducts()) {
            if (prod.getId() == id) {
                return prod;
            }
        }
        return null;
    }
}