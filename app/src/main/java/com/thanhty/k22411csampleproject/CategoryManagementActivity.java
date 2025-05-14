package com.thanhty.k22411csampleproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CategoryManagementActivity extends AppCompatActivity {

    ListView listCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);

        listCategories = findViewById(R.id.listCategories);

        // Dữ liệu mẫu cho danh sách Category
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Electronics");
        categories.add("Clothing");
        categories.add("Books");
        categories.add("Home & Kitchen");
        categories.add("Toys");

        // Sử dụng ArrayAdapter để hiển thị danh sách
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categories);
        listCategories.setAdapter(adapter);
    }
}