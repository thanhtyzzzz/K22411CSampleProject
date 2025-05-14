package com.thanhty.models;

import java.util.ArrayList;

public class ListCategory {
    private ArrayList<Category> categories;

    public ListCategory() {
        categories = new ArrayList<>();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void gen_dataset() {
        Category c1 = new Category();
        c1.setId(1);
        c1.setName("Electronics & Gadgets");
        categories.add(c1);

        Category c2 = new Category();
        c2.setId(2);
        c2.setName("Fashion & Accessories");
        categories.add(c2);

        Category c3 = new Category();
        c3.setId(3);
        c3.setName("Books & Literature");
        categories.add(c3);

        Category c4 = new Category();
        c4.setId(4);
        c4.setName("Home & Kitchen");
        categories.add(c4);

        Category c5 = new Category();
        c5.setId(5);
        c5.setName("Sports & Outdoors");
        categories.add(c5);

        Category c6 = new Category();
        c6.setId(6);
        c6.setName("Beauty & Personal Care");
        categories.add(c6);
    }
}