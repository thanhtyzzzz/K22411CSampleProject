package com.thanhty.k22411csampleproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ProductManagementActivity extends AppCompatActivity {

    ListView listProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);

        listProducts = findViewById(R.id.listProducts);

        // Dữ liệu mẫu cho danh sách Product
        ArrayList<String> products = new ArrayList<>();
        products.add("Laptop - $999");
        products.add("T-Shirt - $19.99");
        products.add("Book: 'Android Development' - $29.99");
        products.add("Blender - $49.99");
        products.add("Toy Car - $9.99");

        // Sử dụng ArrayAdapter để hiển thị danh sách
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, products);
        listProducts.setAdapter(adapter);
    }
}