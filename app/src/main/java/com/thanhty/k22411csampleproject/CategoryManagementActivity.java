package com.thanhty.k22411csampleproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.thanhty.models.Category;
import com.thanhty.models.ListCategory;

public class CategoryManagementActivity extends AppCompatActivity {

    ListView listViewCategories;
    ListCategory listCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_management);
        addViews();
        loadCategories();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addViews() {
        listViewCategories = findViewById(R.id.listViewCategories);
    }

    private void loadCategories() {
        listCategory = new ListCategory();
        listCategory.gen_dataset();
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listCategory.getCategories());
        listViewCategories.setAdapter(adapter);
    }
}