package com.thanhty.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.thanhty.models.Category;
import com.thanhty.models.ListCategory;
import com.thanhty.models.Product;

public class ProductManagementActivity extends AppCompatActivity {

    Spinner spinnerCategory;
    ArrayAdapter<Category> adapterCategory;
    ListCategory listCategory;
    ListView lvProduct;
    ArrayAdapter<Product> adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category c = adapterCategory.getItem(position);
                displayProductByCategory(c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì khi không chọn danh mục
            }
        });

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product p = adapterProduct.getItem(position);
                displayProductDetail(p);
            }
        });
    }

    private void displayProductDetail(Product p) {
        Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
        intent.putExtra("SELECTED_PRODUCT", p);
        startActivity(intent);
    }

    private void displayProductByCategory(Category c) {
        adapterProduct.clear();
        adapterProduct.addAll(c.getProducts());
        adapterProduct.notifyDataSetChanged();
    }

    private void addViews() {
        spinnerCategory = findViewById(R.id.spinnerCategory);
        adapterCategory = new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);
        listCategory = new ListCategory();
        listCategory.generate_sample_product_dataset();
        adapterCategory.addAll(listCategory.getCategories());

        lvProduct = findViewById(R.id.lvProduct);
        adapterProduct = new ArrayAdapter<>(ProductManagementActivity.this,
                android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_new_product) {
            Toast.makeText(ProductManagementActivity.this,
                    "Mở màn hình thêm mới sản phẩm", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.menu_broadcast_advertising) {
            Toast.makeText(ProductManagementActivity.this,
                    "Gửi quảng cáo hàng loạt về sản phẩm", Toast.LENGTH_LONG).show();
            // TODO: Tìm hiểu Firebase Cloud Messaging để gửi thông báo đẩy
        } else if (item.getItemId() == R.id.menu_help) {
            Toast.makeText(ProductManagementActivity.this,
                    "Mở website hướng dẫn sử dụng", Toast.LENGTH_LONG).show();
            // TODO: Mở trình duyệt với URL hướng dẫn
            // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://your-help-website.com"));
            // startActivity(browserIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}