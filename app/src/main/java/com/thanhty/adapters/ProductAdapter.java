package com.thanhty.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thanhty.k22411csampleproject.R;
import com.thanhty.models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    Activity context;
    int resource;

    public ProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        //nhân bản giao diện theo tu vị trí position mà dữ liệu dc duyệt qua:
        View item=inflater.inflate(this.resource, null);
        //lúc này: toàn bộ view nằm trong layout resource (item_advanced_product)
        //sẽ dc mô hình hóa hướng đối tượng và dc quản lý bởi biến item
        //tức là item là Tổng tài View
        //như vậy muốn truy xuất tới các view con trong nó thì phải thông qua item
        ImageView imgProduct=item.findViewById(R.id.imgOrder);
        TextView txtProductId=item.findViewById(R.id.txtProductId);
        TextView txtProductName=item.findViewById(R.id.txtProductName);
        TextView txtProductQuantity=item.findViewById(R.id.txtProductQuantity);
        TextView txtProductPrice=item.findViewById(R.id.txtProductPrice);
        ImageView imgCart=item.findViewById(R.id.imgCart);

        //Lấy mô hiình đối tượng đang dc nhân bản ở vị trí position (đối số 1);
        Product p=getItem(position);
        //Rải dữ liệu của Product lên giao diện trong item
//        imgProduct.setImageResource(p.getImage_id());
//        txtProductId.setText(p.getId());
//        txtProductName.setText(p.getName());
//        txtProductQuantity.setText(p.getQuantity()+"");
//        txtProductPrice.setText(p.getPrice()+"(VND)");
        imgProduct.setImageResource(p.getImage_id());
        txtProductId.setText(p.getId()+"");
        txtProductName.setText(p.getName());
        txtProductQuantity.setText(p.getQuantity()+"");
        txtProductPrice.setText(p.getPrice()+"(VND)");
        //xử lý bấm vào nút mua....tính sau.....

        return item;
    }
}
