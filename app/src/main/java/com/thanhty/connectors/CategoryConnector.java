//package com.thanhty.connectors;
//
//import com.thanhty.models.Category;
//import com.thanhty.models.ListCategory;
//
//public class CategoryConnector {
//    public Category getCategoryById(int id) {
//        ListCategory lc = new ListCategory();
//        lc.gen_dataset();
//        for (Category cat : lc.getCategories()) {
//            if (cat.getId() == id) {
//                return cat;
//            }
//        }
//        return null;
//    }
//}